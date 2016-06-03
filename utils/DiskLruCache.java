package com.immediasemi.blink.utils;

import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class DiskLruCache
  implements Closeable
{
  static final long ANY_SEQUENCE_NUMBER = -1L;
  private static final String CLEAN = "CLEAN";
  private static final String DIRTY = "DIRTY";
  static final String JOURNAL_FILE = "journal";
  static final String JOURNAL_FILE_BACKUP = "journal.bkp";
  static final String JOURNAL_FILE_TEMP = "journal.tmp";
  static final Pattern LEGAL_KEY_PATTERN = Pattern.compile("[a-z0-9_-]{1,120}");
  static final String MAGIC = "libcore.io.DiskLruCache";
  private static final OutputStream NULL_OUTPUT_STREAM = new OutputStream()
  {
    public void write(int paramAnonymousInt)
      throws IOException
    {}
  };
  private static final String READ = "READ";
  private static final String REMOVE = "REMOVE";
  static final String STRING_KEY_PATTERN = "[a-z0-9_-]{1,120}";
  static final String VERSION_1 = "1";
  private final int appVersion;
  private final Callable<Void> cleanupCallable = new Callable()
  {
    public Void call()
      throws Exception
    {
      synchronized (DiskLruCache.this)
      {
        if (DiskLruCache.this.journalWriter == null) {
          return null;
        }
        DiskLruCache.this.trimToSize();
        if (DiskLruCache.this.journalRebuildRequired())
        {
          DiskLruCache.this.rebuildJournal();
          DiskLruCache.access$402(DiskLruCache.this, 0);
        }
      }
    }
  };
  private final File directory;
  final ThreadPoolExecutor executorService = new ThreadPoolExecutor(0, 1, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue());
  private final File journalFile;
  private final File journalFileBackup;
  private final File journalFileTmp;
  private Writer journalWriter;
  private final LinkedHashMap<String, Entry> lruEntries = new LinkedHashMap(0, 0.75F, true);
  private long maxSize;
  private long nextSequenceNumber = 0L;
  private int redundantOpCount;
  private long size = 0L;
  private final int valueCount;
  
  private DiskLruCache(File paramFile, int paramInt1, int paramInt2, long paramLong)
  {
    this.directory = paramFile;
    this.appVersion = paramInt1;
    this.journalFile = new File(paramFile, "journal");
    this.journalFileTmp = new File(paramFile, "journal.tmp");
    this.journalFileBackup = new File(paramFile, "journal.bkp");
    this.valueCount = paramInt2;
    this.maxSize = paramLong;
  }
  
  private void checkNotClosed()
  {
    if (this.journalWriter == null) {
      throw new IllegalStateException("cache is closed");
    }
  }
  
  private void completeEdit(Editor paramEditor, boolean paramBoolean)
    throws IOException
  {
    Object localObject1;
    try
    {
      localObject1 = paramEditor.entry;
      if (((Entry)localObject1).currentEditor != paramEditor)
      {
        paramEditor = new java/lang/IllegalStateException;
        paramEditor.<init>();
        throw paramEditor;
      }
    }
    finally {}
    if ((paramBoolean) && (!((Entry)localObject1).readable)) {
      for (i = 0; i < this.valueCount; i++)
      {
        if (paramEditor.written[i] == 0)
        {
          paramEditor.abort();
          paramEditor = new java/lang/IllegalStateException;
          localObject1 = new java/lang/StringBuilder;
          ((StringBuilder)localObject1).<init>();
          paramEditor.<init>("Newly created entry didn't create value for index " + i);
          throw paramEditor;
        }
        if (!((Entry)localObject1).getDirtyFile(i).exists())
        {
          paramEditor.abort();
          return;
        }
      }
    }
    int i = 0;
    Object localObject2;
    long l1;
    if (i < this.valueCount)
    {
      paramEditor = ((Entry)localObject1).getDirtyFile(i);
      if (paramBoolean) {
        if (paramEditor.exists())
        {
          localObject2 = ((Entry)localObject1).getCleanFile(i);
          paramEditor.renameTo((File)localObject2);
          long l2 = localObject1.lengths[i];
          l1 = ((File)localObject2).length();
          ((Entry)localObject1).lengths[i] = l1;
          this.size = (this.size - l2 + l1);
        }
      }
      for (;;)
      {
        i++;
        break;
        deleteIfExists(paramEditor);
      }
    }
    this.redundantOpCount += 1;
    Entry.access$702((Entry)localObject1, null);
    if ((((Entry)localObject1).readable | paramBoolean))
    {
      Entry.access$602((Entry)localObject1, true);
      paramEditor = this.journalWriter;
      localObject2 = new java/lang/StringBuilder;
      ((StringBuilder)localObject2).<init>();
      paramEditor.write("CLEAN " + ((Entry)localObject1).key + ((Entry)localObject1).getLengths() + '\n');
      if (paramBoolean)
      {
        l1 = this.nextSequenceNumber;
        this.nextSequenceNumber = (1L + l1);
        Entry.access$1202((Entry)localObject1, l1);
      }
    }
    for (;;)
    {
      this.journalWriter.flush();
      if ((this.size <= this.maxSize) && (!journalRebuildRequired())) {
        break;
      }
      this.executorService.submit(this.cleanupCallable);
      break;
      this.lruEntries.remove(((Entry)localObject1).key);
      localObject2 = this.journalWriter;
      paramEditor = new java/lang/StringBuilder;
      paramEditor.<init>();
      ((Writer)localObject2).write("REMOVE " + ((Entry)localObject1).key + '\n');
    }
  }
  
  private static void deleteIfExists(File paramFile)
    throws IOException
  {
    if ((paramFile.exists()) && (!paramFile.delete())) {
      throw new IOException();
    }
  }
  
  private Editor edit(String paramString, long paramLong)
    throws IOException
  {
    Editor localEditor1 = null;
    for (;;)
    {
      Object localObject2;
      try
      {
        checkNotClosed();
        validateKey(paramString);
        localObject2 = (Entry)this.lruEntries.get(paramString);
        if (paramLong != -1L)
        {
          localObject1 = localEditor1;
          if (localObject2 != null)
          {
            long l = ((Entry)localObject2).sequenceNumber;
            if (l != paramLong) {
              localObject1 = localEditor1;
            }
          }
          else
          {
            return (Editor)localObject1;
          }
        }
        if (localObject2 == null)
        {
          localObject1 = new com/immediasemi/blink/utils/DiskLruCache$Entry;
          ((Entry)localObject1).<init>(this, paramString, null);
          this.lruEntries.put(paramString, localObject1);
          localEditor1 = new com/immediasemi/blink/utils/DiskLruCache$Editor;
          localEditor1.<init>(this, (Entry)localObject1, null);
          Entry.access$702((Entry)localObject1, localEditor1);
          localObject1 = this.journalWriter;
          localObject2 = new java/lang/StringBuilder;
          ((StringBuilder)localObject2).<init>();
          ((Writer)localObject1).write("DIRTY " + paramString + '\n');
          this.journalWriter.flush();
          localObject1 = localEditor1;
          continue;
        }
        localEditor2 = ((Entry)localObject2).currentEditor;
      }
      finally {}
      Editor localEditor2;
      Object localObject1 = localObject2;
      if (localEditor2 != null) {
        localObject1 = localEditor1;
      }
    }
  }
  
  private static String inputStreamToString(InputStream paramInputStream)
    throws IOException
  {
    return Util.readFully(new InputStreamReader(paramInputStream, Util.UTF_8));
  }
  
  private boolean journalRebuildRequired()
  {
    if ((this.redundantOpCount >= 2000) && (this.redundantOpCount >= this.lruEntries.size())) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public static DiskLruCache open(File paramFile, int paramInt1, int paramInt2, long paramLong)
    throws IOException
  {
    if (paramLong <= 0L) {
      throw new IllegalArgumentException("maxSize <= 0");
    }
    if (paramInt2 <= 0) {
      throw new IllegalArgumentException("valueCount <= 0");
    }
    Object localObject = new File(paramFile, "journal.bkp");
    File localFile;
    if (((File)localObject).exists())
    {
      localFile = new File(paramFile, "journal");
      if (localFile.exists()) {
        ((File)localObject).delete();
      }
    }
    else
    {
      localObject = new DiskLruCache(paramFile, paramInt1, paramInt2, paramLong);
      if (!((DiskLruCache)localObject).journalFile.exists()) {
        break label181;
      }
    }
    for (;;)
    {
      try
      {
        ((DiskLruCache)localObject).readJournal();
        ((DiskLruCache)localObject).processJournal();
        paramFile = (File)localObject;
        return paramFile;
      }
      catch (IOException localIOException)
      {
        System.out.println("DiskLruCache " + paramFile + " is corrupt: " + localIOException.getMessage() + ", removing");
        ((DiskLruCache)localObject).delete();
      }
      renameTo((File)localObject, localFile, false);
      break;
      label181:
      paramFile.mkdirs();
      paramFile = new DiskLruCache(paramFile, paramInt1, paramInt2, paramLong);
      paramFile.rebuildJournal();
    }
  }
  
  private void processJournal()
    throws IOException
  {
    deleteIfExists(this.journalFileTmp);
    Iterator localIterator = this.lruEntries.values().iterator();
    while (localIterator.hasNext())
    {
      Entry localEntry = (Entry)localIterator.next();
      int i;
      if (localEntry.currentEditor == null)
      {
        for (i = 0; i < this.valueCount; i++) {
          this.size += localEntry.lengths[i];
        }
      }
      else
      {
        Entry.access$702(localEntry, null);
        for (i = 0; i < this.valueCount; i++)
        {
          deleteIfExists(localEntry.getCleanFile(i));
          deleteIfExists(localEntry.getDirtyFile(i));
        }
        localIterator.remove();
      }
    }
  }
  
  private void readJournal()
    throws IOException
  {
    StrictLineReader localStrictLineReader = new StrictLineReader(new FileInputStream(this.journalFile), Util.US_ASCII);
    Object localObject2;
    Object localObject3;
    try
    {
      String str2 = localStrictLineReader.readLine();
      localObject2 = localStrictLineReader.readLine();
      Object localObject4 = localStrictLineReader.readLine();
      localObject3 = localStrictLineReader.readLine();
      String str1 = localStrictLineReader.readLine();
      if ((!"libcore.io.DiskLruCache".equals(str2)) || (!"1".equals(localObject2)) || (!Integer.toString(this.appVersion).equals(localObject4)) || (!Integer.toString(this.valueCount).equals(localObject3)) || (!"".equals(str1)))
      {
        IOException localIOException = new java/io/IOException;
        localObject4 = new java/lang/StringBuilder;
        ((StringBuilder)localObject4).<init>();
        localIOException.<init>("unexpected journal header: [" + str2 + ", " + (String)localObject2 + ", " + (String)localObject3 + ", " + str1 + "]");
        throw localIOException;
      }
    }
    finally
    {
      Util.closeQuietly(localStrictLineReader);
    }
    int i = 0;
    try
    {
      for (;;)
      {
        readJournalLine(localStrictLineReader.readLine());
        i++;
      }
      rebuildJournal();
    }
    catch (EOFException localEOFException)
    {
      this.redundantOpCount = (i - this.lruEntries.size());
      if (!localStrictLineReader.hasUnterminatedLine()) {}
    }
    for (;;)
    {
      Util.closeQuietly(localStrictLineReader);
      return;
      localObject3 = new java/io/BufferedWriter;
      localObject2 = new java/io/OutputStreamWriter;
      FileOutputStream localFileOutputStream = new java/io/FileOutputStream;
      localFileOutputStream.<init>(this.journalFile, true);
      ((OutputStreamWriter)localObject2).<init>(localFileOutputStream, Util.US_ASCII);
      ((BufferedWriter)localObject3).<init>((Writer)localObject2);
      this.journalWriter = ((Writer)localObject3);
    }
  }
  
  private void readJournalLine(String paramString)
    throws IOException
  {
    int k = paramString.indexOf(' ');
    if (k == -1) {
      throw new IOException("unexpected journal line: " + paramString);
    }
    int j = k + 1;
    int i = paramString.indexOf(' ', j);
    Object localObject2;
    Object localObject1;
    if (i == -1)
    {
      localObject2 = paramString.substring(j);
      localObject1 = localObject2;
      if (k != "REMOVE".length()) {
        break label113;
      }
      localObject1 = localObject2;
      if (!paramString.startsWith("REMOVE")) {
        break label113;
      }
      this.lruEntries.remove(localObject2);
    }
    label113:
    do
    {
      for (;;)
      {
        return;
        localObject1 = paramString.substring(j, i);
        Entry localEntry = (Entry)this.lruEntries.get(localObject1);
        localObject2 = localEntry;
        if (localEntry == null)
        {
          localObject2 = new Entry((String)localObject1, null);
          this.lruEntries.put(localObject1, localObject2);
        }
        if ((i != -1) && (k == "CLEAN".length()) && (paramString.startsWith("CLEAN")))
        {
          paramString = paramString.substring(i + 1).split(" ");
          Entry.access$602((Entry)localObject2, true);
          Entry.access$702((Entry)localObject2, null);
          ((Entry)localObject2).setLengths(paramString);
        }
        else
        {
          if ((i != -1) || (k != "DIRTY".length()) || (!paramString.startsWith("DIRTY"))) {
            break;
          }
          Entry.access$702((Entry)localObject2, new Editor((Entry)localObject2, null));
        }
      }
    } while ((i == -1) && (k == "READ".length()) && (paramString.startsWith("READ")));
    throw new IOException("unexpected journal line: " + paramString);
  }
  
  private void rebuildJournal()
    throws IOException
  {
    for (;;)
    {
      StringBuilder localStringBuilder;
      try
      {
        if (this.journalWriter != null) {
          this.journalWriter.close();
        }
        BufferedWriter localBufferedWriter = new java/io/BufferedWriter;
        localObject4 = new java/io/OutputStreamWriter;
        Object localObject2 = new java/io/FileOutputStream;
        ((FileOutputStream)localObject2).<init>(this.journalFileTmp);
        ((OutputStreamWriter)localObject4).<init>((OutputStream)localObject2, Util.US_ASCII);
        localBufferedWriter.<init>((Writer)localObject4);
        try
        {
          localBufferedWriter.write("libcore.io.DiskLruCache");
          localBufferedWriter.write("\n");
          localBufferedWriter.write("1");
          localBufferedWriter.write("\n");
          localBufferedWriter.write(Integer.toString(this.appVersion));
          localBufferedWriter.write("\n");
          localBufferedWriter.write(Integer.toString(this.valueCount));
          localBufferedWriter.write("\n");
          localBufferedWriter.write("\n");
          localObject2 = this.lruEntries.values().iterator();
          if (!((Iterator)localObject2).hasNext()) {
            break;
          }
          localObject4 = (Entry)((Iterator)localObject2).next();
          if (((Entry)localObject4).currentEditor != null)
          {
            localStringBuilder = new java/lang/StringBuilder;
            localStringBuilder.<init>();
            localBufferedWriter.write("DIRTY " + ((Entry)localObject4).key + '\n');
            continue;
            localObject1 = finally;
          }
        }
        finally
        {
          localBufferedWriter.close();
        }
        localStringBuilder = new java/lang/StringBuilder;
      }
      finally {}
      localStringBuilder.<init>();
      ((Writer)localObject1).write("CLEAN " + ((Entry)localObject4).key + ((Entry)localObject4).getLengths() + '\n');
    }
    ((Writer)localObject1).close();
    if (this.journalFile.exists()) {
      renameTo(this.journalFile, this.journalFileBackup, true);
    }
    renameTo(this.journalFileTmp, this.journalFile, false);
    this.journalFileBackup.delete();
    Object localObject4 = new java/io/BufferedWriter;
    OutputStreamWriter localOutputStreamWriter = new java/io/OutputStreamWriter;
    FileOutputStream localFileOutputStream = new java/io/FileOutputStream;
    localFileOutputStream.<init>(this.journalFile, true);
    localOutputStreamWriter.<init>(localFileOutputStream, Util.US_ASCII);
    ((BufferedWriter)localObject4).<init>(localOutputStreamWriter);
    this.journalWriter = ((Writer)localObject4);
  }
  
  private static void renameTo(File paramFile1, File paramFile2, boolean paramBoolean)
    throws IOException
  {
    if (paramBoolean) {
      deleteIfExists(paramFile2);
    }
    if (!paramFile1.renameTo(paramFile2)) {
      throw new IOException();
    }
  }
  
  private void trimToSize()
    throws IOException
  {
    while (this.size > this.maxSize) {
      remove((String)((Map.Entry)this.lruEntries.entrySet().iterator().next()).getKey());
    }
  }
  
  private void validateKey(String paramString)
  {
    if (!LEGAL_KEY_PATTERN.matcher(paramString).matches()) {
      throw new IllegalArgumentException("keys must match regex [a-z0-9_-]{1,120}: \"" + paramString + "\"");
    }
  }
  
  public void close()
    throws IOException
  {
    for (;;)
    {
      try
      {
        Object localObject1 = this.journalWriter;
        if (localObject1 == null) {
          return;
        }
        localObject1 = new java/util/ArrayList;
        ((ArrayList)localObject1).<init>(this.lruEntries.values());
        Iterator localIterator = ((ArrayList)localObject1).iterator();
        if (localIterator.hasNext())
        {
          localObject1 = (Entry)localIterator.next();
          if (((Entry)localObject1).currentEditor == null) {
            continue;
          }
          ((Entry)localObject1).currentEditor.abort();
          continue;
        }
        trimToSize();
      }
      finally {}
      this.journalWriter.close();
      this.journalWriter = null;
    }
  }
  
  public void delete()
    throws IOException
  {
    close();
    Util.deleteContents(this.directory);
  }
  
  public Editor edit(String paramString)
    throws IOException
  {
    return edit(paramString, -1L);
  }
  
  public void flush()
    throws IOException
  {
    try
    {
      checkNotClosed();
      trimToSize();
      this.journalWriter.flush();
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  /* Error */
  public Snapshot get(String paramString)
    throws IOException
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: aload_0
    //   4: monitorenter
    //   5: aload_0
    //   6: invokespecial 317	com/immediasemi/blink/utils/DiskLruCache:checkNotClosed	()V
    //   9: aload_0
    //   10: aload_1
    //   11: invokespecial 320	com/immediasemi/blink/utils/DiskLruCache:validateKey	(Ljava/lang/String;)V
    //   14: aload_0
    //   15: getfield 107	com/immediasemi/blink/utils/DiskLruCache:lruEntries	Ljava/util/LinkedHashMap;
    //   18: aload_1
    //   19: invokevirtual 323	java/util/LinkedHashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   22: checkcast 18	com/immediasemi/blink/utils/DiskLruCache$Entry
    //   25: astore 6
    //   27: aload 6
    //   29: ifnonnull +10 -> 39
    //   32: aload 4
    //   34: astore_3
    //   35: aload_0
    //   36: monitorexit
    //   37: aload_3
    //   38: areturn
    //   39: aload 4
    //   41: astore_3
    //   42: aload 6
    //   44: invokestatic 220	com/immediasemi/blink/utils/DiskLruCache$Entry:access$600	(Lcom/immediasemi/blink/utils/DiskLruCache$Entry;)Z
    //   47: ifeq -12 -> 35
    //   50: aload_0
    //   51: getfield 149	com/immediasemi/blink/utils/DiskLruCache:valueCount	I
    //   54: anewarray 566	java/io/InputStream
    //   57: astore 5
    //   59: iconst_0
    //   60: istore_2
    //   61: iload_2
    //   62: aload_0
    //   63: getfield 149	com/immediasemi/blink/utils/DiskLruCache:valueCount	I
    //   66: if_icmpge +63 -> 129
    //   69: aload 5
    //   71: iload_2
    //   72: new 432	java/io/FileInputStream
    //   75: dup
    //   76: aload 6
    //   78: iload_2
    //   79: invokevirtual 253	com/immediasemi/blink/utils/DiskLruCache$Entry:getCleanFile	(I)Ljava/io/File;
    //   82: invokespecial 434	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   85: aastore
    //   86: iinc 2 1
    //   89: goto -28 -> 61
    //   92: astore_1
    //   93: iconst_0
    //   94: istore_2
    //   95: aload 4
    //   97: astore_3
    //   98: iload_2
    //   99: aload_0
    //   100: getfield 149	com/immediasemi/blink/utils/DiskLruCache:valueCount	I
    //   103: if_icmpge -68 -> 35
    //   106: aload 4
    //   108: astore_3
    //   109: aload 5
    //   111: iload_2
    //   112: aaload
    //   113: ifnull -78 -> 35
    //   116: aload 5
    //   118: iload_2
    //   119: aaload
    //   120: invokestatic 465	com/immediasemi/blink/utils/Util:closeQuietly	(Ljava/io/Closeable;)V
    //   123: iinc 2 1
    //   126: goto -31 -> 95
    //   129: aload_0
    //   130: aload_0
    //   131: getfield 199	com/immediasemi/blink/utils/DiskLruCache:redundantOpCount	I
    //   134: iconst_1
    //   135: iadd
    //   136: putfield 199	com/immediasemi/blink/utils/DiskLruCache:redundantOpCount	I
    //   139: aload_0
    //   140: getfield 155	com/immediasemi/blink/utils/DiskLruCache:journalWriter	Ljava/io/Writer;
    //   143: astore 4
    //   145: new 229	java/lang/StringBuilder
    //   148: astore_3
    //   149: aload_3
    //   150: invokespecial 230	java/lang/StringBuilder:<init>	()V
    //   153: aload 4
    //   155: aload_3
    //   156: ldc_w 568
    //   159: invokevirtual 236	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   162: aload_1
    //   163: invokevirtual 236	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   166: bipush 10
    //   168: invokevirtual 289	java/lang/StringBuilder:append	(C)Ljava/lang/StringBuilder;
    //   171: invokevirtual 243	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   174: invokevirtual 571	java/io/Writer:append	(Ljava/lang/CharSequence;)Ljava/io/Writer;
    //   177: pop
    //   178: aload_0
    //   179: invokespecial 183	com/immediasemi/blink/utils/DiskLruCache:journalRebuildRequired	()Z
    //   182: ifeq +15 -> 197
    //   185: aload_0
    //   186: getfield 127	com/immediasemi/blink/utils/DiskLruCache:executorService	Ljava/util/concurrent/ThreadPoolExecutor;
    //   189: aload_0
    //   190: getfield 132	com/immediasemi/blink/utils/DiskLruCache:cleanupCallable	Ljava/util/concurrent/Callable;
    //   193: invokevirtual 305	java/util/concurrent/ThreadPoolExecutor:submit	(Ljava/util/concurrent/Callable;)Ljava/util/concurrent/Future;
    //   196: pop
    //   197: new 21	com/immediasemi/blink/utils/DiskLruCache$Snapshot
    //   200: dup
    //   201: aload_0
    //   202: aload_1
    //   203: aload 6
    //   205: invokestatic 327	com/immediasemi/blink/utils/DiskLruCache$Entry:access$1200	(Lcom/immediasemi/blink/utils/DiskLruCache$Entry;)J
    //   208: aload 5
    //   210: aload 6
    //   212: invokestatic 261	com/immediasemi/blink/utils/DiskLruCache$Entry:access$1000	(Lcom/immediasemi/blink/utils/DiskLruCache$Entry;)[J
    //   215: aconst_null
    //   216: invokespecial 574	com/immediasemi/blink/utils/DiskLruCache$Snapshot:<init>	(Lcom/immediasemi/blink/utils/DiskLruCache;Ljava/lang/String;J[Ljava/io/InputStream;[JLcom/immediasemi/blink/utils/DiskLruCache$1;)V
    //   219: astore_3
    //   220: goto -185 -> 35
    //   223: astore_1
    //   224: aload_0
    //   225: monitorexit
    //   226: aload_1
    //   227: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	228	0	this	DiskLruCache
    //   0	228	1	paramString	String
    //   60	64	2	i	int
    //   34	186	3	localObject	Object
    //   1	153	4	localWriter	Writer
    //   57	152	5	arrayOfInputStream	InputStream[]
    //   25	186	6	localEntry	Entry
    // Exception table:
    //   from	to	target	type
    //   61	86	92	java/io/FileNotFoundException
    //   5	27	223	finally
    //   42	59	223	finally
    //   61	86	223	finally
    //   98	106	223	finally
    //   116	123	223	finally
    //   129	197	223	finally
    //   197	220	223	finally
  }
  
  public File getDirectory()
  {
    return this.directory;
  }
  
  public long getMaxSize()
  {
    try
    {
      long l = this.maxSize;
      return l;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  /* Error */
  public boolean isClosed()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 155	com/immediasemi/blink/utils/DiskLruCache:journalWriter	Ljava/io/Writer;
    //   6: astore_2
    //   7: aload_2
    //   8: ifnonnull +9 -> 17
    //   11: iconst_1
    //   12: istore_1
    //   13: aload_0
    //   14: monitorexit
    //   15: iload_1
    //   16: ireturn
    //   17: iconst_0
    //   18: istore_1
    //   19: goto -6 -> 13
    //   22: astore_2
    //   23: aload_0
    //   24: monitorexit
    //   25: aload_2
    //   26: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	27	0	this	DiskLruCache
    //   12	7	1	bool	boolean
    //   6	2	2	localWriter	Writer
    //   22	4	2	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   2	7	22	finally
  }
  
  public boolean remove(String paramString)
    throws IOException
  {
    for (;;)
    {
      int i;
      try
      {
        checkNotClosed();
        validateKey(paramString);
        localObject2 = (Entry)this.lruEntries.get(paramString);
        if (localObject2 != null)
        {
          localObject1 = ((Entry)localObject2).currentEditor;
          if (localObject1 == null) {}
        }
        else
        {
          bool = false;
          return bool;
        }
        i = 0;
        if (i >= this.valueCount) {
          break label152;
        }
        localObject1 = ((Entry)localObject2).getCleanFile(i);
        if ((((File)localObject1).exists()) && (!((File)localObject1).delete()))
        {
          paramString = new java/io/IOException;
          localObject2 = new java/lang/StringBuilder;
          ((StringBuilder)localObject2).<init>();
          paramString.<init>("failed to delete " + localObject1);
          throw paramString;
        }
      }
      finally {}
      this.size -= localObject2.lengths[i];
      ((Entry)localObject2).lengths[i] = 0L;
      i++;
      continue;
      label152:
      this.redundantOpCount += 1;
      Object localObject1 = this.journalWriter;
      Object localObject2 = new java/lang/StringBuilder;
      ((StringBuilder)localObject2).<init>();
      ((Writer)localObject1).append("REMOVE " + paramString + '\n');
      this.lruEntries.remove(paramString);
      if (journalRebuildRequired()) {
        this.executorService.submit(this.cleanupCallable);
      }
      boolean bool = true;
    }
  }
  
  public void setMaxSize(long paramLong)
  {
    try
    {
      this.maxSize = paramLong;
      this.executorService.submit(this.cleanupCallable);
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public long size()
  {
    try
    {
      long l = this.size;
      return l;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public final class Editor
  {
    private boolean committed;
    private final DiskLruCache.Entry entry;
    private boolean hasErrors;
    private final boolean[] written;
    
    private Editor(DiskLruCache.Entry paramEntry)
    {
      this.entry = paramEntry;
      if (DiskLruCache.Entry.access$600(paramEntry)) {}
      for (this$1 = null;; this$1 = new boolean[DiskLruCache.this.valueCount])
      {
        this.written = DiskLruCache.this;
        return;
      }
    }
    
    public void abort()
      throws IOException
    {
      DiskLruCache.this.completeEdit(this, false);
    }
    
    public void abortUnlessCommitted()
    {
      if (!this.committed) {}
      try
      {
        abort();
        return;
      }
      catch (IOException localIOException)
      {
        for (;;) {}
      }
    }
    
    public void commit()
      throws IOException
    {
      if (this.hasErrors)
      {
        DiskLruCache.this.completeEdit(this, false);
        DiskLruCache.this.remove(DiskLruCache.Entry.access$1100(this.entry));
      }
      for (;;)
      {
        this.committed = true;
        return;
        DiskLruCache.this.completeEdit(this, true);
      }
    }
    
    public String getString(int paramInt)
      throws IOException
    {
      Object localObject = newInputStream(paramInt);
      if (localObject != null) {}
      for (localObject = DiskLruCache.inputStreamToString((InputStream)localObject);; localObject = null) {
        return (String)localObject;
      }
    }
    
    public InputStream newInputStream(int paramInt)
      throws IOException
    {
      IllegalStateException localIllegalStateException = null;
      synchronized (DiskLruCache.this)
      {
        if (DiskLruCache.Entry.access$700(this.entry) != this)
        {
          localIllegalStateException = new java/lang/IllegalStateException;
          localIllegalStateException.<init>();
          throw localIllegalStateException;
        }
      }
      if (!DiskLruCache.Entry.access$600(this.entry)) {}
      for (;;)
      {
        return localInputStream;
        try
        {
          FileInputStream localFileInputStream = new java/io/FileInputStream;
          localFileInputStream.<init>(this.entry.getCleanFile(paramInt));
          Object localObject = localFileInputStream;
        }
        catch (FileNotFoundException localFileNotFoundException) {}
      }
    }
    
    public OutputStream newOutputStream(int paramInt)
      throws IOException
    {
      if ((paramInt < 0) || (paramInt >= DiskLruCache.this.valueCount)) {
        throw new IllegalArgumentException("Expected index " + paramInt + " to " + "be greater than 0 and less than the maximum value count " + "of " + DiskLruCache.this.valueCount);
      }
      synchronized (DiskLruCache.this)
      {
        if (DiskLruCache.Entry.access$700(this.entry) != this)
        {
          IllegalStateException localIllegalStateException = new java/lang/IllegalStateException;
          localIllegalStateException.<init>();
          throw localIllegalStateException;
        }
      }
      if (!DiskLruCache.Entry.access$600(this.entry)) {
        this.written[paramInt] = true;
      }
      Object localObject3 = this.entry.getDirtyFile(paramInt);
      try
      {
        Object localObject2 = new java/io/FileOutputStream;
        ((FileOutputStream)localObject2).<init>((File)localObject3);
        localObject3 = new com/immediasemi/blink/utils/DiskLruCache$Editor$FaultHidingOutputStream;
        ((FaultHidingOutputStream)localObject3).<init>(this, (OutputStream)localObject2, null);
        localObject2 = localObject3;
        return (OutputStream)localObject2;
      }
      catch (FileNotFoundException localFileNotFoundException1)
      {
        for (;;)
        {
          DiskLruCache.this.directory.mkdirs();
          try
          {
            FileOutputStream localFileOutputStream = new FileOutputStream((File)localObject3);
          }
          catch (FileNotFoundException localFileNotFoundException2)
          {
            OutputStream localOutputStream = DiskLruCache.NULL_OUTPUT_STREAM;
          }
        }
      }
    }
    
    /* Error */
    public void set(int paramInt, String paramString)
      throws IOException
    {
      // Byte code:
      //   0: aconst_null
      //   1: astore_3
      //   2: new 158	java/io/OutputStreamWriter
      //   5: astore 4
      //   7: aload 4
      //   9: aload_0
      //   10: iload_1
      //   11: invokevirtual 160	com/immediasemi/blink/utils/DiskLruCache$Editor:newOutputStream	(I)Ljava/io/OutputStream;
      //   14: getstatic 166	com/immediasemi/blink/utils/Util:UTF_8	Ljava/nio/charset/Charset;
      //   17: invokespecial 169	java/io/OutputStreamWriter:<init>	(Ljava/io/OutputStream;Ljava/nio/charset/Charset;)V
      //   20: aload 4
      //   22: aload_2
      //   23: invokevirtual 174	java/io/Writer:write	(Ljava/lang/String;)V
      //   26: aload 4
      //   28: invokestatic 178	com/immediasemi/blink/utils/Util:closeQuietly	(Ljava/io/Closeable;)V
      //   31: return
      //   32: astore_2
      //   33: aload_3
      //   34: invokestatic 178	com/immediasemi/blink/utils/Util:closeQuietly	(Ljava/io/Closeable;)V
      //   37: aload_2
      //   38: athrow
      //   39: astore_2
      //   40: aload 4
      //   42: astore_3
      //   43: goto -10 -> 33
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	46	0	this	Editor
      //   0	46	1	paramInt	int
      //   0	46	2	paramString	String
      //   1	42	3	localObject	Object
      //   5	36	4	localOutputStreamWriter	OutputStreamWriter
      // Exception table:
      //   from	to	target	type
      //   2	20	32	finally
      //   20	26	39	finally
    }
    
    private class FaultHidingOutputStream
      extends FilterOutputStream
    {
      private FaultHidingOutputStream(OutputStream paramOutputStream)
      {
        super();
      }
      
      public void close()
      {
        try
        {
          this.out.close();
          return;
        }
        catch (IOException localIOException)
        {
          for (;;)
          {
            DiskLruCache.Editor.access$2302(DiskLruCache.Editor.this, true);
          }
        }
      }
      
      public void flush()
      {
        try
        {
          this.out.flush();
          return;
        }
        catch (IOException localIOException)
        {
          for (;;)
          {
            DiskLruCache.Editor.access$2302(DiskLruCache.Editor.this, true);
          }
        }
      }
      
      public void write(int paramInt)
      {
        try
        {
          this.out.write(paramInt);
          return;
        }
        catch (IOException localIOException)
        {
          for (;;)
          {
            DiskLruCache.Editor.access$2302(DiskLruCache.Editor.this, true);
          }
        }
      }
      
      public void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
      {
        try
        {
          this.out.write(paramArrayOfByte, paramInt1, paramInt2);
          return;
        }
        catch (IOException paramArrayOfByte)
        {
          for (;;)
          {
            DiskLruCache.Editor.access$2302(DiskLruCache.Editor.this, true);
          }
        }
      }
    }
  }
  
  private final class Entry
  {
    private DiskLruCache.Editor currentEditor;
    private final String key;
    private final long[] lengths;
    private boolean readable;
    private long sequenceNumber;
    
    private Entry(String paramString)
    {
      this.key = paramString;
      this.lengths = new long[DiskLruCache.this.valueCount];
    }
    
    private IOException invalidLengths(String[] paramArrayOfString)
      throws IOException
    {
      throw new IOException("unexpected journal line: " + Arrays.toString(paramArrayOfString));
    }
    
    private void setLengths(String[] paramArrayOfString)
      throws IOException
    {
      if (paramArrayOfString.length != DiskLruCache.this.valueCount) {
        throw invalidLengths(paramArrayOfString);
      }
      int i = 0;
      try
      {
        while (i < paramArrayOfString.length)
        {
          this.lengths[i] = Long.parseLong(paramArrayOfString[i]);
          i++;
        }
        return;
      }
      catch (NumberFormatException localNumberFormatException)
      {
        throw invalidLengths(paramArrayOfString);
      }
    }
    
    public File getCleanFile(int paramInt)
    {
      return new File(DiskLruCache.this.directory, this.key + "." + paramInt);
    }
    
    public File getDirtyFile(int paramInt)
    {
      return new File(DiskLruCache.this.directory, this.key + "." + paramInt + ".tmp");
    }
    
    public String getLengths()
      throws IOException
    {
      StringBuilder localStringBuilder = new StringBuilder();
      for (long l : this.lengths) {
        localStringBuilder.append(' ').append(l);
      }
      return localStringBuilder.toString();
    }
  }
  
  public final class Snapshot
    implements Closeable
  {
    private final InputStream[] ins;
    private final String key;
    private final long[] lengths;
    private final long sequenceNumber;
    
    private Snapshot(String paramString, long paramLong, InputStream[] paramArrayOfInputStream, long[] paramArrayOfLong)
    {
      this.key = paramString;
      this.sequenceNumber = paramLong;
      this.ins = paramArrayOfInputStream;
      this.lengths = paramArrayOfLong;
    }
    
    public void close()
    {
      InputStream[] arrayOfInputStream = this.ins;
      int j = arrayOfInputStream.length;
      for (int i = 0; i < j; i++) {
        Util.closeQuietly(arrayOfInputStream[i]);
      }
    }
    
    public DiskLruCache.Editor edit()
      throws IOException
    {
      return DiskLruCache.this.edit(this.key, this.sequenceNumber);
    }
    
    public InputStream getInputStream(int paramInt)
    {
      return this.ins[paramInt];
    }
    
    public long getLength(int paramInt)
    {
      return this.lengths[paramInt];
    }
    
    public String getString(int paramInt)
      throws IOException
    {
      return DiskLruCache.inputStreamToString(getInputStream(paramInt));
    }
  }
}


/* Location:              /home/zips/Android/Apktool/Blink4Home/Blink-136-dex2jar.jar!/com/immediasemi/blink/utils/DiskLruCache.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */