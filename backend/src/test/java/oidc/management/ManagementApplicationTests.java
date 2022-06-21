package oidc.management;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;

@ExtendWith(MockitoExtension.class)
public class ManagementApplicationTests {

	@Mock
	private ConfigurableApplicationContext configurableApplicationContext;

	@Test
	public void testMain() {
		// Mock SpringApplication
		try (MockedStatic<SpringApplication> mock = Mockito.mockStatic(SpringApplication.class)) {

			// Mock run
			mock.when(() -> SpringApplication.run(Mockito.eq(ManagementApplication.class), Mockito.any(String[].class)))
					.thenReturn(configurableApplicationContext);

			// Test main
			ManagementApplication.main(new String[] {});
		}
	}

}
