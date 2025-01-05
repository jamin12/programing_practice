package hello.config;

import org.springframework.context.annotation.Bean;

import hello.datasource.MyDataSource;
import hello.datasource.MyDataSourcePropertiesV2;
import hello.datasource.MyDataSourcePropertiesV3;
import lombok.extern.slf4j.Slf4j;

@Slf4j
// @EnableConfigurationProperties(MyDataSourcePropertiesV1.class)
public class MyDataSourceConfigV3 {
	private final MyDataSourcePropertiesV3 properties;

	public MyDataSourceConfigV3(MyDataSourcePropertiesV3 properties) {
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