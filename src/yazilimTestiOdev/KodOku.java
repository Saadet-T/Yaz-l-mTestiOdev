/**
*
* @author B191210025
* @since 07,07,2023
* <p>
* Dosya okuma işlemlerinin gerçeklendiği dosya
* </p>
*/

package yazilimTestiOdev;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import Interfaces.IKodOku;
//Bu sınıf verilen dosyayı okuyup her satır sonuna bir \n ekliyor ki yorumlar kaldırılırken bir sorun oluşmasın.
public class KodOku implements IKodOku {
	public String dosyaOku(String dosyaYolu) {
        String data = "";
        try {
              File file = new File(dosyaYolu);
              Scanner myReader = new Scanner(file);
              while (myReader.hasNextLine()) {
                data += myReader.nextLine();
                data +="\n";
              }
              
              myReader.close();
            } catch (FileNotFoundException e) {
              System.out.println("Dosya okumada bir hata yasandi.");
            }

        return data;
    }
}
