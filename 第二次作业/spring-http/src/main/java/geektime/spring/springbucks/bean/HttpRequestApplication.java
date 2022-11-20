package geektime.spring.springbucks.bean;

import lombok.extern.slf4j.*;
import okhttp3.*;
import okhttp3.MediaType;
import org.apache.http.*;
import org.apache.http.HttpEntity;
import org.apache.http.client.*;
import org.apache.http.client.methods.*;
import org.apache.http.impl.client.*;
import org.apache.http.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.web.client.*;
import org.springframework.context.annotation.*;
import org.springframework.http.*;
import org.springframework.web.client.*;
import org.springframework.web.reactive.function.client.*;
import org.springframework.web.util.*;
import reactor.core.publisher.*;

import java.io.*;
import java.net.*;
import java.util.*;

@Slf4j
@SpringBootApplication
public class HttpRequestApplication implements ApplicationRunner {
	@Autowired
	private OkHttpClient okHttpClient;
	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private WebClient webClient;

		String requestUrl = "http://localhost:8081/allByJson";
		String requestUrl2 = "http://localhost:8081/allByXml";

	public static void main (String[] args) {
		SpringApplication.run (HttpRequestApplication.class, args);
	}
	@Bean
	public WebClient webClient(WebClient.Builder builder) {
		return builder.build ();
	}

	@Bean
	public RestTemplate restTemplate (RestTemplateBuilder builder) {
		return builder.build ();
	}


	@Override
	public void run (ApplicationArguments args) throws Exception {

		/**
		 * okHttp
		 */
		String s = postJsonParams (requestUrl, "", null);
		System.out.println ("s = " + s);

		/**
		 *
		 *RestTemplate
		 */
//
		URI uri = UriComponentsBuilder
				.fromUriString(requestUrl)
				.build().toUri ();
		RequestEntity<Void> req = RequestEntity.get (uri)
				.accept (org.springframework.http.MediaType.APPLICATION_JSON)
				.build ();
		ResponseEntity<String> resp = restTemplate.exchange (req, String.class);
		System.out.println ("resp = " + resp);

		ResponseEntity<String> xmlstr = restTemplate.getForEntity(requestUrl2, String.class);
		System.out.println ("xmlstr = " + xmlstr);

		/**
		 * WebClient
		 */
		WebClient webClient = WebClient.create();
		Mono<String> mono = webClient.get().uri(requestUrl).retrieve().bodyToMono(String.class);
		System.out.println ("mono = " + mono.block ());


		/**
		 * HttpClient
		 */

		HttpGet httpGet = new HttpGet(requestUrl);
		String str = sendHttpGet (httpGet);
		System.out.println ("s = " + str);

		/**
		 * httpURLConnection
		 */

		String s1= httpURLConnection (requestUrl);
		System.out.println ("s1 = " + s1);


	}


	private String postJsonParams (String url, String jsonParams, Map<String, String> map) {
		String responseBody = "";
		MediaType mediaType = MediaType.parse ("application/json;charset=utf-8");
		RequestBody stringBody = RequestBody.create (mediaType, jsonParams);

		Request.Builder builder = new Request.Builder ().url (url);
		if (null != map && ! map.isEmpty ()) {
			for (Map.Entry<String, String> entry : map.entrySet ()) {
				builder.addHeader (entry.getKey (), entry.getValue ());
			}
		}
		Request request = builder.post (stringBody).build ();
		log.info ("接口调用入参: {}", request);
		Response response = null;
		try {
			response = okHttpClient.newCall (request).execute ();
			log.error ("接口调用返参: {}", response);
			if (response.isSuccessful ()) {
				ResponseBody body = response.body ();
				if (null != body) {
					return body.string ();
				}
			}
		} catch (IOException e) {
			log.error ("okhttp3 post error IOException >> ex = {}", e.getMessage ());
		} catch (RuntimeException e) {
			log.error ("okhttp3 post error >> RuntimeException ex = {}", e.getMessage ());
		} finally {
			if (null != response) {
				response.close ();
			}
		}
		return responseBody;
	}

	private String sendHttpGet(HttpGet httpGet) {
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		HttpEntity entity = null;
		String responseContent = null;
		try {
			// 创建默认的httpClient实例.
			httpClient = HttpClients.createDefault();

			// 执行请求
			response = httpClient.execute(httpGet);
			entity = response.getEntity ();
			responseContent = EntityUtils.toString(entity, "UTF-8");
		} catch (ParseException e) {
			throw new RuntimeException (e);
		} catch (ClientProtocolException e) {
			throw new RuntimeException (e);
		} catch (IOException e) {
			throw new RuntimeException (e);
		} catch (RuntimeException e) {
			log.error(e.getMessage());
		} finally {
			try {
				// 关闭连接,释放资源
				if (null != response) {
					response.close();
				}
				if (null != httpClient) {
					httpClient.close();
				}
			} catch (IOException e) {
				log.error(e.getMessage());
			}
		}
		return responseContent;
	}

	private String httpURLConnection(String url) throws IOException, RuntimeException {
		HttpURLConnection connection = null;
		InputStream inputStream = null;
		try {
			URL url1 = new URL (url);
			connection = (HttpURLConnection) url1.openConnection ();
			connection.setRequestMethod ("GET");
			connection.setRequestProperty ("Content-Type", String.valueOf (org.springframework.http.MediaType.APPLICATION_JSON));

			if (200 != connection.getResponseCode ()) {
				throw new IOException ("请求失败");
			}

			inputStream = connection.getInputStream ();
			byte[] bytes = new byte[inputStream.available ()];
			inputStream.read (bytes);
			return new String (bytes);
		} catch (IOException e) {
			throw new RuntimeException (e);
		} finally {
			if (null != inputStream) {
				inputStream.close ();
			}
			if (null != connection) {
				connection.disconnect ();
			}
		}

	}


}
