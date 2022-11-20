package geektime.spring.springbucks.bean.config;

import okhttp3.*;
import org.springframework.context.annotation.*;
import javax.net.ssl.*;
import java.security.*;
import java.security.cert.*;
import java.util.*;
import java.util.concurrent.*;

@Configuration
public class OkHttpConfiguration {
	@Bean
	public OkHttpClient okHttpClient () {
		return new OkHttpClient.Builder ()
				.sslSocketFactory (Objects.requireNonNull (sslSocketFactory ()), x509TrustManager ())
				.retryOnConnectionFailure (false)
				.connectionPool (pool ())
				.connectTimeout (10L, TimeUnit.SECONDS)
				.readTimeout (10L, TimeUnit.SECONDS)
				.writeTimeout (10L, TimeUnit.SECONDS)
				.build ();
	}

	@Bean
	public X509TrustManager x509TrustManager () {
		return new MyX509TrustManager ();
	}


	@Bean
	public SSLSocketFactory sslSocketFactory () {
		try {
			SSLContext sslContext = SSLContext.getInstance ("SSL");
			sslContext.init (null, new TrustManager[] {x509TrustManager ()}, new SecureRandom ());
			return sslContext.getSocketFactory ();
		} catch (NoSuchAlgorithmException | KeyManagementException e) {
			e.printStackTrace ();
		}
		return null;
	}


	@Bean
	public ConnectionPool pool () {
		return new ConnectionPool (200, 5, TimeUnit.MINUTES);
	}

	private static class MyX509TrustManager implements X509TrustManager {

		static final X509Certificate[] X_509_CERTIFICATES = new X509Certificate[0];

		MyX509TrustManager () {
		}

		@Override
		public void checkClientTrusted (X509Certificate[] x509Certificates, String s) {
		}

		@Override
		public void checkServerTrusted (X509Certificate[] x509Certificates, String s) {
		}

		@Override
		public X509Certificate[] getAcceptedIssuers () {
			return X_509_CERTIFICATES;
		}
	}
}
