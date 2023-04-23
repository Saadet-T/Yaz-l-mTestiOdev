package yazilimTestiOdev;

import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.CoreMatchers.containsString;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.github.javafaker.Code;
import com.github.javafaker.Faker;

import Interfaces.IKodOku;
import Interfaces.IKodTemizleme;

@ExtendWith(MockitoExtension.class)
class KodTemizlemeTest {
	@Mock
	IKodOku kodOku;

	@Test
	@DisplayName("Tekli yorumları düzgün temizliyor mu?")//Burada kodu temizledikten sonra "//" yani tek satir yorum kodu içeriyor mu kontrol ediliyor
	void TekliYorumSatırı() {
		KodTemizleme kodTemizleme = new KodTemizleme();
		Mockito.when(kodOku.dosyaOku("Ogrenci.java")).thenReturn(TestStrings.testKodOgrenci);
		assertFalse(kodTemizleme.yorumSil(kodOku.dosyaOku("Ogrenci.java")).contains("//"));
	
	}
	
	@RepeatedTest(5)
    @DisplayName("Tekli yorumları düzgün temizliyor mu repetead?")
	void TekliYorumSatırıRepeated() {
		KodTemizleme kodTemizleme = new KodTemizleme();
		assertFalse(kodTemizleme.yorumSil(TestStrings.testKodOgrenci).contains("//"));
	
	}
	
	@ParameterizedTest
	@DisplayName("Coklu yorumları düzgün temizliyor mu?")
	@CsvSource({TestStrings.testKodDeneme,TestStrings.testKodOgrenci})//Burada kodu temizledikten sonra "/*" ve "*\" yani çoklu satir yorum kodunun başlangıcını ve sonunu içeriyor mu kontrol ediliyor
	void CokluYorumSatırı(String yorumluKod) {
		KodTemizleme kodTemizleme = new KodTemizleme();
		assertFalse(kodTemizleme.yorumSil(yorumluKod).contains("/*"));
		assertFalse(kodTemizleme.yorumSil(yorumluKod).contains("*/"));
	
	}
	@RepeatedTest(5)
	@DisplayName("Coklu yorumları düzgün temizliyor mu repeated?")
    void repatedCokluYorum() {
		OperatorBul operatorBul = new OperatorBul();
		KodTemizleme kodTemizleme = new KodTemizleme();
		assertFalse(kodTemizleme.yorumSil(TestStrings.testKodOgrenci).contains("/*"));
		assertFalse(kodTemizleme.yorumSil(TestStrings.testKodOgrenci).contains("*/"));
		
    }
	
	@Test
	@DisplayName("Faker Yorum Silme Test")
    void fakerYorumTest() {
		OperatorBul operatorBul = new OperatorBul();
		KodTemizleme kodTemizleme = new KodTemizleme();
		Faker faker = new Faker();
	    String kod = "public class " + faker.letterify("????").toUpperCase() + " {\n\n";
	    kod += "\tprivate static final String USER = \"" + faker.name().fullName() + " - " + faker.address().fullAddress() + "\";\n\n";
	    kod += "\tpublic static void main(String[] args) {\n";
	    kod += "\t\tint " + faker.letterify("?") + faker.letterify("?") + " = " + faker.random().nextInt(10) + ";\n";
	    kod += "\t\tfor (int i = 0; i < " + faker.random().nextInt(10) + "; i++) {\n";
	    kod += "\t\t\t" + faker.letterify("?") + faker.letterify("?") + " += " + faker.random().nextInt(10) + ";\n";
	    kod += "\t\t}\n";
	    kod += "\t\t\n /* Bu kısım sayıların toplandığı alan*///TestDataYorum";
	    kod += "\t}\n\n String outPut=\"The user info is:\"+USER; ";
	    kod += "}";
	    assertFalse(kodTemizleme.yorumSil(kod).contains("/*"));
	    assertFalse(kodTemizleme.yorumSil(TestStrings.testKodOgrenci).contains("*/"));
	    assertFalse(kodTemizleme.yorumSil(TestStrings.testKodOgrenci).contains("//"));
	    }
	
	@Test
	@DisplayName("Faker Yorum Satır Sonu Silme  Test")
    void fakerYeniSatırTest() {
		OperatorBul operatorBul = new OperatorBul();
		KodTemizleme kodTemizleme = new KodTemizleme();
		Faker faker = new Faker();

	    String kod = "public class " + faker.letterify("????").toUpperCase() + " {\n\n";
	    kod += "\tprivate static final String USER = \"" + faker.name().fullName() + " - " + faker.address().fullAddress() + "\";\n\n";
	    kod += "\tpublic static void main(String[] args) {\n";
	    kod += "\t\tint " + faker.letterify("?") + faker.letterify("?") + " = " + faker.random().nextInt(10) + ";\n";
	    kod += "\t\tfor (int i = 0; i < " + faker.random().nextInt(10) + "; i++) {\n";
	    kod += "\t\t\t" + faker.letterify("?") + faker.letterify("?") + " += " + faker.random().nextInt(10) + ";\n";
	    kod += "\t\t}\n";
	    kod += "\t\t\n /* Bu kısım sayıların toplandığı alan*///TestDataYorum";
	    kod += "\t}\n\n String outPut=\"The user info is:\"+USER; ";
	    kod += "}";
	    assertFalse(kodTemizleme.yorumSil(TestStrings.testKodOgrenci).contains("\r"));
	    assertFalse(kodTemizleme.yorumSil(TestStrings.testKodOgrenci).contains("\n"));
	    }
	@Test
    @DisplayName("Kod Okuma ve Kod Temizleme entegrasyon testi ")
    void KodTemizlemeveOkuma() {
		KodOku kodOku = new KodOku();
		KodTemizleme kodTemizleme = new KodTemizleme();
		String temizKod= kodTemizleme.yorumSil(TestStrings.testKodOgrenci);
	    assertFalse(kodTemizleme.yorumSil(temizKod).contains("/*"));
	    assertFalse(kodTemizleme.yorumSil(temizKod).contains("*/"));
	    assertFalse(kodTemizleme.yorumSil(temizKod).contains("//"));
	    assertFalse(kodTemizleme.yorumSil(temizKod).contains("\r"));
	    assertFalse(kodTemizleme.yorumSil(temizKod).contains("\n"));
    }
    }
	
	
