package geektime.spring.springbucks.bean;

import lombok.extern.slf4j.*;
import okhttp3.*;
import okhttp3.MediaType;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.web.client.*;
import org.springframework.context.annotation.*;
import org.springframework.http.*;
import org.springframework.web.client.*;
import org.springframework.web.util.*;

import java.io.*;
import java.net.*;
import java.util.*;
@Slf4j
@SpringBootApplication
public class HttpRequestApplication implements ApplicationRunner{
	@Autowired
	private OkHttpClient okHttpClient;
	@Autowired
	private RestTemplate restTemplate;

	String requestUrl = "http://localhost:8081/allByJson";

	public static void main(String[] args) {
		SpringApplication.run(HttpRequestApplication.class, args);
	}
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
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

		URI uri = UriComponentsBuilder
				.fromUriString(requestUrl)
				.build(1);
		RequestEntity<Void> req = RequestEntity.get(uri)
				.accept(org.springframework.http.MediaType.APPLICATION_JSON)
				.build();
		ResponseEntity<String> resp = restTemplate.exchange(req, String.class);
		System.out.println ("resp = " + resp);


	}


	public String postJsonParams (String url, String jsonParams, Map<String, String> map) {
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


}
