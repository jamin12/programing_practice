package hello.datasource;

import java.time.Duration;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;

import lombok.Getter;

@Getter
@ConfigurationProperties("my.datasource")
public class MyDataSourcePropertiesV2 {
	private final String url;
	private final String username;
	private final String password;
	private final Etc etc;

	public MyDataSourcePropertiesV2(String url, String username, String
		password, @DefaultValue Etc etc) {
		this.url = url;
		this.username = username;
		this.password = password;
		this.etc = etc;
	}

	public record Etc(int maxConnection, Duration timeout, List<String> options) {
		public Etc(int maxConnection, Duration timeout, @DefaultValue("DEFAULT")
		List<String> options) {
			this.maxConnection = maxConnection;
			this.timeout = timeout;
			this.options = options;
		}
	}
}