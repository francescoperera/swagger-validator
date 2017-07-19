import java.io.File


import com.typesafe.scalalogging.LazyLogging
import io.circe.parser
import scala.io.Source
import sys.process._


object Test extends LazyLogging{

  private val body = "payload"
  private val filesDir = "files"
  private var recordsCounter = 0
  private var deserializedRecords = 0

  def main(args: Array[String]): Unit = {
    val ymlFilePath: String = args(0)
    val s3bucketPath: String = args(1)
    "chmod 775 swaggerGen.sh" #| s"./swaggerGen.sh $ymlFilePath $s3bucketPath" !

    val files: List[String] = getFileNames(filesDir)
    compareFilesWithModel(files)
    logger.info(s"Success: ${deserializedRecords.toString}/${recordsCounter.toString} " +
      s" records were deserialized with given swagger spec.")
  }

  def compareFilesWithModel( fileNames: List[String]) = {

    def readFile(f: String) = {
      val bufferedSource = Source.fromFile(f)
      for (line <- bufferedSource.getLines) {
        deserializeJsonRecord(line)
        recordsCounter +=1
      }
      bufferedSource.close
    }

    fileNames.foreach( fileName => readFile(fileName))

  }


  def deserializeJsonRecord(str:String) = parser.parse(str) match {
    case Left(failure) =>
      //      logger.warn("FAILURE:")
      //      logger.error("REASON:" + failure.toString)
      None
    case Right(json) =>
      val record: String = json.asObject.get.apply(body).get.noSpaces
      ApiInvoker.deserialize(record,"", classOf[Location])
      deserializedRecords +=1

  }

  def getFileNames(dirName: String): List[String] = {

    def recursiveListFiles(f: File): List[File] = {
      val these = f.listFiles.toList
      these ++ these.filter(_.isDirectory).flatMap(recursiveListFiles)
    }

    recursiveListFiles(new File(dirName)).filter( f => f.getName.endsWith(".part")).map(_.getAbsolutePath)

  }
}