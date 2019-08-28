package co.hewanq.hewanq;

import android.widget.CheckBox;
import android.widget.EditText;

public class InputChecker {
    private static int error, checkboxError;

    public static int getError()
    {
        return error;
    }

    public static int getCheckboxError()
    {
        return checkboxError;
    }

    public static String checkAndGetString(EditText eT)
    {
         /**
         @Kegunaan : Untuk mengecek kosongkah input dari EditText dan mengambil nilai Stringnya

         Jika kosong maka error dan mengembalikan nilai null
         Jika ada isinya maka akan mengembalikan isi dari input

         */

         error = 0;

        if(eT.getText().toString().trim().length() == 0)
        {
            eT.setError("Tidak boleh kosong!");
            error = 1;
            return null;
        }
        else
        {
            return eT.getText().toString();
        }
    }

    public static String checkboxToString(CheckBox args[])
    {
         /**
          @Kegunaan : Untuk mengubah data checkbox ke String
          @Perhatian : URUTAN CHECKBOX HARUS SAMA DENGAN DATA ARRAY INI ["Kucing", "Burung", "Hamster", "Ikan", "Anjing"]

         */

        String hasil="", data[] = {"Kucing", "Burung", "Hamster", "Ikan", "Anjing"};
        checkboxError = 0;
        error = 0;
        int awal=0;

        for(int i=0; i<args.length; i++)
        {
            if(args[i].isChecked())
            {
                if(awal != 0)
                {
                    hasil += ", ";
                }
                awal=1;
                hasil += data[i];
            }
        }

        // Jika tidak ada yang di pilih, maka error
        if(awal == 0)
        {
            checkboxError = 1;
            error = 1;
        }

        return hasil;
    }

    public static int spinnerTranslatorFromJenisHewan(String data)
    {
        /**
         @Kegunaan : Untuk mengubah data spinner dengan tipe jenis hewan ke integer

         */
        int hasil;

        switch (data)
        {
            case "Kucing":
                hasil = 0;
                break;
            case "Burung":
                hasil = 1;
                break;
            case "Hamster":
                hasil = 2;
                break;
            case "Ikan" :
                hasil = 3;
                break;
            case "Anjing" :
                hasil = 4;
                break;
            default:
                hasil = 10;
                break;
        }

        return hasil;
    }

    public static int spinnerTranslatorFromGender(String data)
    {
        /**
         @Kegunaan : Untuk mengubah data spinner dengan tipe gender ke integer

         */
        int hasil;

        switch (data)
        {
            case "Laki-laki":
                hasil = 0;
                break;
            case "Perempuan":
                hasil = 1;
                break;
            default:
                hasil = 10;
                break;
        }

        return hasil;
    }
}
