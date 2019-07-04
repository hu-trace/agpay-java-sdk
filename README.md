# WAYA Pay JAVA SDK

使用说明
## 初始化
经过SDK的封装，初始化非常简单，在程序启动后添加以下代码进行初始化

初始化会自动调用换取token的接口，并创建定时器，每日凌晨1点1分进行更新token
```java
DefaultTokenConfig.build("your apiId", "your apiSecret");
TokenTask.start();
```

## 调用接口
使用``HttpModule``接口，非常简化了调用以及新增接口

通过``HttpClient``构造``HttpModule``
>以创建微信交易订单举例
```java
HttpModule module = HttpClient.buildWxpayOrder();
HashMap<String, Object> param = new HashMap<>();
// 需要给param添加必要参数
module.parameter(param);
JSONObject resp = module.execute();
if(resp.getIntValue("code") == 0) {
  // 成功
  JSONObject data = resp.getJSONObject("data");
}else {
  // 失败了
  System.out.println(resp.getString("msg"));
}
```
