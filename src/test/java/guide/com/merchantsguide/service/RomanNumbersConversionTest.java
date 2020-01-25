package guide.com.merchantsguide.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;

import guide.com.merchantsguide.Inputs;

@RunWith(MockitoJUnitRunner.class)
@PropertySource("classpath:inputs.yml")
public class RomanNumbersConversionTest {
	
	@Autowired
	private Inputs inputs;
	
	@InjectMocks
	private RomanNumbersConversion conversionService;

	@Mock
	private RomanNumberGenrator generator;
	
	@Test
	public void testProcessInput() throws Exception {
		//RomanNumberGenrator generator = mock(RomanNumberGenrator.class);
		when(generator.generateCreditValue(any(), any(), any())).thenReturn(-1d);
		conversionService.processInput(inputs.getInvalidInputs());
		verify(generator, never()).generateCreditValue(any(), any(), any());
		assertThat("Map is not created", conversionService.interGalacticRomanMapping.isEmpty());
	};
}
