package hello.config;

import org.springframework.context.annotation.Bean;

import hello.datasource.MyDataSource;
import hello.datasource.MyDataSourcePropertiesV1;
import hello.datasource.MyDataSourcePropertiesV2;
import lombok.extern.slf4j.Slf4j;

@Slf4j
// @EnableConfigurationProperties(MyDataSourcePropertiesV1.class)
public class MyDataSourceConfigV2 {
	private final MyDataSourcePropertiesV2 properties;

	public MyDataSourceConfigV2(MyDataSourcePropertiesV2 properties) {
		this.properties = properties;
	}

	@Bean
	public MyDataSource dataSource() {
		return new MyDataSource(
			properties.getUrl(),
			properties.getUsername(),
			properties.getPassword(),
			properties.getEtc().maxConnection(),
			properties.getEtc().timeout(),
			properties.getEtc().options());
	}
}