/**
*
* @author B191210025
* @since 07,07,2023
* <p>
* Operatorlerin sayısal olarak bulunduğu sınıf
* </p>
*/
package yazilimTestiOdev;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OperatorBul {

	    private static final String regexTekSayisal = "\\(*[a-zA-Z_][a-zA-Z0-9]*\\)*(\\+\\+|\\-\\-)|(\\+\\+|\\-\\-)\\(*[a-zA-Z_][a-zA-Z0-9]*\\)*";
	    private static final String regexSayisal = "\\(*[a-zA-Z_][a-zA-Z0-9]*\\)*\\h*(\\*|\\/|\\\\%|\\^|((?<!=)=(?!=))|((?<!\\-)-(?!\\-))|((?<!\\&)&(?!\\&))|((?<!\\+)\\+(?!\\+))|\\+=|\\-=|\\*=|\\/=|\\%=|\\&=|\\|=|\\^=)\\h*\\(*([a-zA-Z_][a-zA-Z0-9]*\\)|(\\\\\\\")|(\\'))*";
	    //Sayısal ifadeleri arıyor ama == ile = karışmasın diye ileri ve geri arama var aynısı -- , ++ ve && içinde geçerli
	    private static final String regexIliskisel = "(\\b([a-zA-Z_][a-zA-Z0-9]*)\\b|\\b([0-9]+)\\b)\\s*((?:<=|<|>=|>|==)\\s*)(\\b([a-zA-Z_][a-zA-Z0-9]*)\\b|\\b([0-9]+)\\b)";   
	    private static final String regexIliskiselTek= "(\\b([a-zA-Z_][a-zA-Z0-9]*)\\b|\\b([0-9]+)\\b)\\s*((?:!=)\\s*)(\\b([a-zA-Z_][a-zA-Z0-9]*)\\b|\\b([0-9]+)\\b)";
	    //İliskisel ve mantıksal da ! ayrımı için ayrı yazılmış regex
	    private static final String regexMantiksal = "\\|\\||&&|!";
	    private static final String regexFonksiyon="\\b(public|private|protected)?\\s*(static\\s+)?\\w+\\s+\\w+\\s*\\([^)]*\\)\\s*\\{";
	    //Fonksiyonların başlangıç şekillerine göre bulunabileceği bir regex ifadesi
	    
        int countTekSayisal = 0;
        int countSayisal = 0;
        int countIliskisel = 0;
        int countIliskiselTek = 0;
        int countTekMantiksal = 0;
        int countMantiksal = 0;
        int countFonksiyon = 0;
        
        KodTemizleme kodTemizleme = new KodTemizleme();
	  //Sayım fonksiyonları burada gerçekleniyor raporda ayrıntılı açıklamıştım   
	   int totalCount(String regex ,String data) {
		   int count = 0;
	        Pattern patternSayisal = Pattern.compile(regex);
	        Matcher matcherSayisal = patternSayisal.matcher(data);
	        while (matcherSayisal.find()) {
	        	count++;
	            while (matcherSayisal.find()) {
	            	count++;
	            }
	            data = matcherSayisal.replaceAll("regexVariable");
	            matcherSayisal = patternSayisal.matcher(data);
	        }
	        return count;
	   }
	   
	     
	   public int tekSayisal(String data) {
		  String yorumsuzData= kodTemizleme.yorumSil(data);
		   countTekSayisal = totalCount(regexTekSayisal, yorumsuzData);
		   return countTekSayisal;
		   
	   } 
	   public int contSayisal(String data) {
	
		   String yorumsuzData=kodTemizleme.yorumSil(data);
		   countSayisal = totalCount(regexSayisal,yorumsuzData);
		   return countSayisal;
		   
	   } 
 
	   public int iliskiselOperatorSayi(String data) {

		   String yorumsuzData= kodTemizleme.yorumSil(data);
		   countIliskisel = totalCount(regexIliskisel,yorumsuzData);
		   return countIliskisel;
		   
	   } 
	   public int iliskiselOperatorSayiTek(String data) {

		   String yorumsuzData= kodTemizleme.yorumSil(data);
		   countIliskiselTek = totalCount(regexIliskiselTek,yorumsuzData);
		   return countIliskiselTek;
		   
	   }
	   public int iliskiselOperatorSayisi(String data) {

		   return  iliskiselOperatorSayiTek(data)+iliskiselOperatorSayi(data);
		   
	   }
//	   public int tekMantiksal(String data) {
//
//		   String yorumsuzData= kodTemizleme.yorumSil(data);
//		   countTekMantiksal = totalCount(regexTekMantiksal,yorumsuzData);
//		   return countTekMantiksal;
//		   
//	   } 
	   public int contMantiksal(String data) {
		   
		   String yorumsuzData= kodTemizleme.yorumSil(data);
		   countMantiksal = totalCount(regexMantiksal,yorumsuzData);
		   return countMantiksal-iliskiselOperatorSayiTek(data);
		   
	   } 
	   public int fonksiyonSayisi(String data) {
		   
		   String yorumsuzData= kodTemizleme.yorumSil(data);
		   countFonksiyon = totalCount(regexFonksiyon,yorumsuzData);
		   return countFonksiyon;
		   
	   }
	   
	   public int sayisalOperatorSayisi(String data) {
			return  contSayisal(data)+tekSayisal(data);
		     
	   }
	   public int mantiksalOperatorSayisi(String data) {
		   return contMantiksal(data);
	   }
	   //İki operandı etkileyen operatorler ikili operator
	   public int ikiliOperatorSayisi(String data) {
		   return contMantiksal(data)+contSayisal(data)+iliskiselOperatorSayisi(data);
	   }
	   public int tekliOperatorSayisi(String data) {
		   return tekSayisal(data);
	   }

	   public int operandSayisi(String data) {
		   return(((contSayisal(data)+iliskiselOperatorSayisi(data)+mantiksalOperatorSayisi(data))*2))+tekSayisal(data);
	   }

}
	    
	    
