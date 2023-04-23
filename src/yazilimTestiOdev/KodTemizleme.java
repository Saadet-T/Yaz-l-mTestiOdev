/**
*
* @author B191210025
* @since 07,07,2023
* <p>
* Verilen dosyanın içeriğinden yorumları satır sonlarını kaldıran sınıf.
* </p>
*/
package yazilimTestiOdev;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Interfaces.IKodTemizleme;

	public class KodTemizleme implements IKodTemizleme{
		
	   public String yorumSil(String data) {
		    String regexComment = "\\/\\*[\\S\\s]*?\\*/|//[^\\n]*"; //Burada önce yorumlar kaldırılıyor ki tekli yorumlarda satır sonlarına bağlı kaldırılsın
	        Pattern patternComment = Pattern.compile(regexComment);
	        Matcher matcherComment = patternComment.matcher(data);
	        String dataYorumsuz = matcherComment.replaceAll("");  
	        String tekSatirData = dataYorumsuz.replaceAll("[\r\n]+", "");//Ardından \n \r kaldırılıp string tek satıra indirgeniyor.
	       
		   return tekSatirData;
	   }
}
