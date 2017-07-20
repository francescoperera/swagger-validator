import java.io.File

import com.typesafe.scalalogging.LazyLogging
import io.circe.parser
import io.swagger.client.ApiInvoker
import io.swagger.client.model._
import sys.process._
import scala.language.postfixOps
import scala.io.Source


object Test extends LazyLogging{

  private val body = "payload"
  private val filesDir = "files"
  private var recordsCounter = 0
  private var deserializedRecords = 0

  def main(args: Array[String]): Unit = {
//    val ymlFilePath: String = args(0)
//    val s3bucketPath: String = args(1)
//    "chmod 775 swaggerGen.sh" #| s"./swaggerGen.sh $ymlFilePath $s3bucketPath" !

    val files: List[String] = getFileNames(filesDir)
    compareFilesWithModel(files)
    logger.info(s"Success: ${deserializedRecords.toString}/${recordsCounter.toString} " +
      s" records were deserialized with given swagger spec.")

//    "sudo chmod 775 cleanup.sh" #| s"./cleanup.sh" !


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
      ApiInvoker.deserialize(record,"", classOf[UserEnrollment]) //FIXME: how to point to model here before script is run - FP 07/18
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