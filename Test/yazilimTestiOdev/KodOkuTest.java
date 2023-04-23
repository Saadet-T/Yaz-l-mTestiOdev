package yazilimTestiOdev;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.*;



class KodOkuTest {
	static String kod1="Bu dosyanın okunduğunun bir kanıtıdır.";
	static String kod2 ="Bu dosyanın okunduğunun kanıtıdır\n"
			+ "Bu da ikinci satır";
	
	//Dosyanın sorunsuz okunduğunu kanıtlamak için parametreli dosya okuma testi
	@ParameterizedTest
    @MethodSource("kodOkuTest")
    void kodOkuTest(String kodString,String filepath) {
		KodOku kodOku = new KodOku();
		assertTrue(kodOku.dosyaOku(filepath).contains(kodString));
    }
	static Stream<Arguments> kodOkuTest() {
        List<Arguments> kodOkuTest = Arrays.asList(
                Arguments.of(kod1,"OkumaTest.java"),
                Arguments.of(kod2,"OkumaTest2.java")
        );
        return kodOkuTest.stream();
 
    }
	

 
}
