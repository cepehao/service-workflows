package ru.psu.workflow.serviceworkflows.services

import io.minio.BucketExistsArgs
import io.minio.GetObjectArgs
import io.minio.MinioClient
import io.minio.PutObjectArgs
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import ru.psu.workflow.serviceworkflows.repositories.IRepositoryTasks

@Service
class CServiceTasks(val repositoryTasks: IRepositoryTasks): IServiceTasks
{
    val bucketName = "tasks-bucket"

    val minioClient = MinioClient.builder()
        .endpoint("http://127.0.0.1:9000")
        .credentials("minioadmin", "minioadmin")
        .build()



    override fun addPythonScript(taskId: String, pythonScript: MultipartFile) {

        val optionalTask = repositoryTasks.findById(taskId)
        val task = optionalTask.get()

        val objectName = pythonScript.getOriginalFilename()
        val inputStream = pythonScript.getInputStream()
        val objectSize= pythonScript.getSize()

        minioClient!!.putObject(
            PutObjectArgs.builder().
                bucket(bucketName)
                    .`object`(objectName)
                    .stream(inputStream, objectSize, -1)
                    .contentType("script/py")
                    .build()
        )

        task.fileName = objectName
        repositoryTasks.save(task)
    }

    override fun getPythonScript(taskId: String): String {

        val optionalTask = repositoryTasks.findById(taskId)
        val task = optionalTask.get()
        val objectName = task.fileName


        val stream = minioClient!!.getObject(
                        GetObjectArgs.builder()
                            .bucket(bucketName)
                            .`object`(objectName)
                            .build()
        )

        return String(stream.readAllBytes())
    }
}