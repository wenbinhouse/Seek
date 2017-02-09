package wb.app.seek.common.http.entity;

/**
 * Json 节点名
 *
 * Created by mwb on 16/9/25.
 */

public class JsonNode {

  /**
   * 服务器正常响应状态代码, 200
   */
  int RESPONSE_STATUS_200 = 200;

  /**
   * "数据"节点名称
   */
  String NODE_NAME_DATA = "data";

  /**
   * "错误码"节点名称
   */
  String NODE_NAME_ERROR_CODE = "errorCode";

  /**
   * "异常描述"节点名称
   */
  String NODE_NAME_EXCEPTION = "exception";

  String NODE_NAME_MSG = "msg";

  /**
   * "请求结果"节点名称
   */
  String NODE_NAME_RESULT = "result";
}
