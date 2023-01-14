import java.io.*;
import java.util.zip.*;

public class zipFiles {

        public static void ReadFunction(String[] args)
        {
            try(FileInputStream fin=new FileInputStream("input.txt");
                FileOutputStream fos=new FileOutputStream("output.txt"))
            {
                byte[] buffer = new byte[fin.available()];
                fin.read(buffer, 0, buffer.length);
                fos.write(buffer, 0, buffer.length);
            }
            catch(IOException ex){
                System.out.println(ex.getMessage());
            }


        }

    public static void ZipReadFunction(String[] args){
        try(ZipInputStream zin = new ZipInputStream(new FileInputStream("C:\\SomeDir\\output.zip")))
        {
            ZipEntry entry;
            String name;
            long size;
            while((entry=zin.getNextEntry())!=null){

                name = entry.getName();
                size=entry.getSize();
                System.out.printf("File name: %s \t File size: %d \n", name, size);


                FileOutputStream fout = new FileOutputStream("C:\\somedir\\new" + name);
                for (int c = zin.read(); c != -1; c = zin.read()) {
                    fout.write(c);
                }
                fout.flush();
                zin.closeEntry();
                fout.close();
            }
        }
        catch(Exception ex){

            System.out.println(ex.getMessage());
        }
    }

    public static void ZipMakeFunction(String[] args) {

        String filename = "C:\\SomeDir\\notes.txt";
        try(ZipOutputStream zout = new ZipOutputStream(new FileOutputStream("C:\\SomeDir\\output.zip"));
            FileInputStream fis= new FileInputStream(filename);)
        {
            ZipEntry entry1=new ZipEntry("notes.txt");
            zout.putNextEntry(entry1);
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            zout.write(buffer);
            zout.closeEntry();
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }



    public static void main(String[] args) {

    }
}

