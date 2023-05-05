package basic.services;

import basic.util.DateUtil;
import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.UUID;

/**
 * 阿里云OSS Service
 */
@Service
public class AliyunOssService {
    final String accessKeyId = "LTAI5tSYN3WQm9PMntwVjXZn";
    final String accessKeySecret = "1HlZ2emCR73YRg0wryrvIbeCl4HEX6";
    final String endpoint = "oss-cn-shenzhen.aliyuncs.com";
    final String bucketName = "jovem";
    private final OSS ossClient;

    // 创建OSSClient实例
    public AliyunOssService() {
        ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
    }

    public String getOssFile(String objectName) {
        return "https://" + bucketName + "." + endpoint + "/" + objectName;
    }

    /**
     * 上传文件到阿里云OSS
     *
     * @param objectName 上传到OSS后的文件名
     * @param filePath   本地文件路径
     * @return
     */
    public boolean uploadFile(String objectName, String filePath) {
        try {
            // 上传文件
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objectName, new File(filePath));
            putObjectRequest.setProcess("true");
            // 上传字符串。
            PutObjectResult result = ossClient.putObject(putObjectRequest);
            // 如果上传成功，则返回200。
            System.out.println(result.getResponse().getStatusCode());
            return result.getResponse().isSuccessful();
        } catch  (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
        return false;
    }

    public String randomFilePath(String fileType) {
        return DateUtil.dateTime() + UUID.randomUUID() + fileType;
    }

}
