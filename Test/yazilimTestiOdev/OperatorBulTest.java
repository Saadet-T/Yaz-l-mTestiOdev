package yazilimTestiOdev;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.github.javafaker.Faker;

import Interfaces.IKodTemizleme;

@ExtendWith(MockitoExtension.class)
class OperatorBulTest {
	@Mock
	IKodTemizleme kodTemizleme;
	
	@Test
	@DisplayName("! ve != ayırt ediliyor mu ?")
	void mantiksalUnlemTest() {
		Mockito.when(kodTemizleme.yorumSil(Mockito.anyString())).thenReturn(TestStrings.mantiksalUnlemTestYorumsuz);
		OperatorBul operatorBul = new OperatorBul();
		assertEquals(3,operatorBul.contMantiksal(kodTemizleme.yorumSil(TestStrings.mantiksalUnlemTest)));
	}
	
	@RepeatedTest(5)
    @DisplayName("Ünlem Ayrımı Testi Repeated Test ")
    void repatedUnlemTest() {
		OperatorBul operatorBul = new OperatorBul();
		assertEquals(3,operatorBul.contMantiksal(TestStrings.mantiksalUnlemTestYorumsuz));
    }
	
	
	@Test
	@DisplayName("Eşittirleri sayisal olarak ayirt edebiliyor mu?")
	void esittirAyrimTestSayisal() {
		Mockito.when(kodTemizleme.yorumSil(Mockito.anyString())).thenReturn(TestStrings.yorumsuzTestKodEsittir);
		OperatorBul operatorBul = new OperatorBul();
		assertEquals(0,operatorBul.contMantiksal(kodTemizleme.yorumSil(TestStrings.testKodEsittir)));
	}
	@Test
	@DisplayName("Faker Eşittir Sayısal Ayrımı Test")
    void fakerYeniSatırTest() {
		OperatorBul operatorBul = new OperatorBul();
		Faker faker = new Faker();

	    String kod = "public class " + faker.letterify("????").toUpperCase() + " {";
	    kod += "private static final String USER = \"" + faker.name().fullName() + " " + faker.address().fullAddress() + "\";";
	    kod += "public static void main(String[] args) {";
	    kod += "int " + faker.letterify("?") + faker.letterify("?") + " = " + faker.random().nextInt(10) + ";";
	    kod += "for (int i = 0; i < " + faker.random().nextInt(10) + "; i++) {";
	    kod += "" + faker.letterify("?") + faker.letterify("?") + " += " + faker.random().nextInt(10) + ";";
	    kod += "}";
	    kod += " /* Bu kısım sayıların toplandığı alan*///TestDataYorum";
	    kod += "} String outPut=\"The user info is:\"+USER; ";
	    kod += "}";
	    assertEquals(5,operatorBul.contSayisal(kod));
	    }
	
	@RepeatedTest(5)
    @DisplayName("Sayisal eşittir ayrım repeated test ")
    void repatedSayisalTest() {
		OperatorBul operatorBul = new OperatorBul();
		assertEquals(7,operatorBul.contSayisal(TestStrings.yorumsuzTestKodEsittir));
    }
	
	@Test
	@DisplayName("Eşittirleri ilişkisel ayirt edebiliyor mu?")
	void esittirAyrimTestIliskisel() {
		OperatorBul operatorBul = new OperatorBul();
		assertEquals(4,operatorBul.iliskiselOperatorSayi(TestStrings.yorumsuzTestKodEsittir));
	}
	
	@RepeatedTest(5)
    @DisplayName("İlişkisel eşittir ayrim repeated test. ")
    void repatedIliskiselTest() {
		OperatorBul operatorBul = new OperatorBul();
		assertEquals(4,operatorBul.iliskiselOperatorSayi(TestStrings.yorumsuzTestKodEsittir));
    }
	
	
	@ParameterizedTest
	 @DisplayName("Tek sayısal operatorler parametreli test ")
    @MethodSource("tekSayisalTest")
    void tekSayisalOperatorTestEt(String yorumsuzKod, int parametreSayisi) {
		OperatorBul operatorBul = new OperatorBul();
		assertEquals(parametreSayisi,operatorBul.tekSayisal(yorumsuzKod));
    }
	/*CSV uzun ve karmaşık stringlerde çok sorun çıkarttığı ve girdi olarakta değişkene atanmış stringi
	 * kabul etmediği için argümanlı bir şekilde Parametreli testi gerçekleştirdim */
	static Stream<Arguments> tekSayisalTest() {
        List<Arguments> tekSayisalTest = Arrays.asList(
                Arguments.of(TestStrings.yorumsuzTestKodDeneme,1),
                Arguments.of(TestStrings.yorumsuzTestKodOgrenci,3)
        );
        return tekSayisalTest.stream();
    }
	@RepeatedTest(5)
    @DisplayName("Tek sayisal Repeated Test. ")
    void repatedTekSayisalTest() {
		OperatorBul operatorBul = new OperatorBul();
		assertEquals(3,operatorBul.tekSayisal(TestStrings.yorumsuzTestKodOgrenci));
    }
	@Test
    @DisplayName("Kod temizleme ve Sayisal operator bulma entegrasyon testi ")
    void entegrasyonSayisalTemizleme() {
		OperatorBul operatorBul = new OperatorBul();
		KodTemizleme kodTemizleme = new KodTemizleme();
		String sayisalTemiz= kodTemizleme.yorumSil(TestStrings.testKodOgrenci);
		assertEquals(3,operatorBul.tekSayisal(sayisalTemiz));
    }

	
	@ParameterizedTest
	 @DisplayName("Çift operator sayisi parametreli test")
    @MethodSource("ciftOperatorSayisiTest")
    void ciftOperatorSayisiTest(String yorumsuzKod, int parametreSayisi) {
		OperatorBul operatorBul = new OperatorBul();
		assertEquals(parametreSayisi,operatorBul.ikiliOperatorSayisi(yorumsuzKod));
    }
	static Stream<Arguments> ciftOperatorSayisiTest() {
        List<Arguments> ciftOperatorSayisiTest = Arrays.asList(
                Arguments.of(TestStrings.yorumsuzTestKodDeneme,11),
                Arguments.of(TestStrings.yorumsuzTestKodOgrenci,18)
        );
        return ciftOperatorSayisiTest.stream();
        
    }
	@Test
    @DisplayName("Kod temizleme ve iliskisel operator bulma entegrasyon testi ")
    void KodTemizlemeveIlıskisel() {
		OperatorBul operatorBul = new OperatorBul();
		KodTemizleme kodTemizleme = new KodTemizleme();
		String iliskiselTemiz= kodTemizleme.yorumSil(TestStrings.testKodOgrenci);
		assertEquals(5,operatorBul.iliskiselOperatorSayisi(iliskiselTemiz));
    }
	
	@ParameterizedTest
    @MethodSource("iliskiselOperatorTekTest")
    void iliskiselOperatorTekTest(String yorumsuzKod, int parametreSayisi) {
		OperatorBul operatorBul = new OperatorBul();
		assertEquals(parametreSayisi,operatorBul.iliskiselOperatorSayiTek(yorumsuzKod));
    }
	static Stream<Arguments> iliskiselOperatorTekTest() {
        List<Arguments> iliskiselOperatorTekTest = Arrays.asList(
                Arguments.of(TestStrings.yorumsuzTestKodDeneme,1),
                Arguments.of(TestStrings.yorumsuzTestKodOgrenci,0)
        );
        return iliskiselOperatorTekTest.stream();
 
    }
	
	@ParameterizedTest
	 @DisplayName("İlişkisel operator sayısı parametreli test")
    @MethodSource("iliskiselOperatorSayisiTest")
    void iliskiselOperatorSayisiTest(String yorumsuzKod, int parametreSayisi) {
		OperatorBul operatorBul = new OperatorBul();
		assertEquals(parametreSayisi,operatorBul.iliskiselOperatorSayisi(yorumsuzKod));
    }
	static Stream<Arguments> iliskiselOperatorSayisiTest() {
        List<Arguments> iliskiselOperatorSayisiTest = Arrays.asList(
                Arguments.of(TestStrings.yorumsuzTestKodDeneme,3),
                Arguments.of(TestStrings.yorumsuzTestKodOgrenci,5)
        );
        return iliskiselOperatorSayisiTest.stream();
        
        /*
         * İlişkisel Operatörleri Kontrol ederken ilk kullandığım ifade;
         * \(*[a-zA-Z_][a-zA-Z0-9]*\)*\h*(<=|<|>=|>|==|!=)\h*\(*[a-zA-Z_][a-zA-Z0-9]*\)*
         * ifadesiydi . Bu regex ifadesinde sayılarla değişkenlerin ve sayıların karşılaştırmasını
         * içermediği için ilk testi geçiyor ama ikinci parametre testinde test başarısız oluyordu. 
         * Böylelikle regex ifademi yeniden düzenledim
       */
    }
	@Test
	@DisplayName("İlişkisel Operator Faker Test")
    void fakerIliskiselOperator() {
		OperatorBul operatorBul = new OperatorBul();
		Faker faker = new Faker();
	    String kod = "public class " + faker.letterify("????").toUpperCase() + " {";
	    kod += "private static final String USER = \"" + faker.name().fullName() + " " + faker.address().fullAddress() + "\";";
	    kod += "public static void main(String[] args) {";
	    kod += "int " + faker.letterify("?") + faker.letterify("?") + " = " + faker.random().nextInt(10) + ";";
	    kod += "for (int i = 0; i < " + faker.random().nextInt(10) + "; i++) {";
	    kod += "" + faker.letterify("?") + faker.letterify("?") + " += " + faker.random().nextInt(10) + ";";
	    kod += "}";
	    kod += " /* Bu kısım sayıların toplandığı alan*///TestDataYorum";
	    kod += "} String outPut=\"The user info is:\"+USER; ";
	    kod += "}";
	    assertEquals(1,operatorBul.iliskiselOperatorSayisi(kod));
	    }
	
	@ParameterizedTest
	 @DisplayName("Mantıksal operator Sayısı Parametreli Test")
    @MethodSource("mantiksalOperatorSayisiTest")
    void mantiksalOperatorSayisiTest(String yorumsuzKod, int parametreSayisi) {
		OperatorBul operatorBul = new OperatorBul();
		assertEquals(parametreSayisi,operatorBul.mantiksalOperatorSayisi(yorumsuzKod));
    }
	static Stream<Arguments> mantiksalOperatorSayisiTest() {
        List<Arguments> mantiksalOperatorSayisiTest = Arrays.asList(
                Arguments.of(TestStrings.yorumsuzTestKodDeneme,2),
                Arguments.of(TestStrings.yorumsuzTestKodOgrenci,3)
        );
        return mantiksalOperatorSayisiTest.stream();
        
        
    }
	
	@Test
	@DisplayName("Mantıksal Operator Faker Test")
    void fakerMantiksalOperator() {
		OperatorBul operatorBul = new OperatorBul();
		Faker faker = new Faker();

	    String kod = "public class " + faker.letterify("????").toUpperCase() + " {";
	    kod += "private static final String USER = \"" + faker.name().fullName() + " " + faker.address().fullAddress() + "\";";
	    kod += "public static void main(String[] args) {";
	    kod += "int " + faker.letterify("?") + faker.letterify("?") + " = " + faker.random().nextInt(10) + ";";
	    kod += "for (int i = 0; i < " + faker.random().nextInt(10) + "; i++) {";
	    kod += "" + faker.letterify("?") + faker.letterify("?") + " += " + faker.random().nextInt(10) + ";";
	    kod += "}";
	    kod += " /* Bu kısım ''sayıların &&toplandığı alan*///Test||DataYorum";
	    kod += "} String outPut=\"The user info is:\"+USER; ";
	    kod += "}";
	    assertEquals(0,operatorBul.mantiksalOperatorSayisi(kod));
	    }
	
	@ParameterizedTest
	 @DisplayName("Operand Sayisi Parametreli Test")
    @MethodSource("operandSayisiTest")
    void operandSayisiTest(String yorumsuzKod, int parametreSayisi) {
		OperatorBul operatorBul = new OperatorBul();
		assertEquals(parametreSayisi,operatorBul.operandSayisi(yorumsuzKod));
    }
	static Stream<Arguments> operandSayisiTest() {
        List<Arguments> operandSayisiTest = Arrays.asList(
                Arguments.of(TestStrings.yorumsuzTestKodDeneme,23),
                Arguments.of(TestStrings.yorumsuzTestKodOgrenci,33)
        );
        return operandSayisiTest.stream();
        
    }
	
	@ParameterizedTest
    @DisplayName("Fonksiyon Testi Parametreli Entegrasyon Testi")
	 @MethodSource("fonksiyonSayisiTest")
    void fonksiyonSayisiTest(String yorumluKod, int parametreSayisi) {
		KodTemizleme kodTemizleme = new KodTemizleme();
		OperatorBul operatorBul = new OperatorBul();
		String temizKod= kodTemizleme.yorumSil(yorumluKod);
		assertEquals(parametreSayisi,operatorBul.fonksiyonSayisi(temizKod));
    }
	static Stream<Arguments> fonksiyonSayisiTest() {
        List<Arguments> fonksiyonSayisiTest = Arrays.asList(
                Arguments.of(TestStrings.testKodDeneme,5),
                Arguments.of(TestStrings.testKodOgrenci,5)
        );
        return fonksiyonSayisiTest.stream();
        
    }
	
	@RepeatedTest(5)
    @DisplayName("Fonksiyon Repeated Test. ")
    void repatedFonkiyonTest() {
		OperatorBul operatorBul = new OperatorBul();
		assertEquals(5,operatorBul.fonksiyonSayisi(TestStrings.yorumsuzTestKodOgrenci));
    }
	@Test
    @DisplayName("Kod Okuma , Kod Temizleme ve Operator Bulma Entegrasyon/Bütünlük testi ")
    void KodTemizlemeveOkuma() {
		KodOku kodOku = new KodOku();
		KodTemizleme kodTemizleme = new KodTemizleme();
		OperatorBul operatorBul = new OperatorBul();
		String temizKod= kodTemizleme.yorumSil(TestStrings.testKodOgrenci);
		assertEquals(3,operatorBul.mantiksalOperatorSayisi(temizKod));
    }


}
