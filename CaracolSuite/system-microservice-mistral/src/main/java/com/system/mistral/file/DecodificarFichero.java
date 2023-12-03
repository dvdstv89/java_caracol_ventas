package com.system.mistral.file;

public class DecodificarFichero {
//    public void DecodificarFichero(byte[] ficheroDesdeBD)
//    {
//        if (File.Exists(direccionFicheroZIPtemporal))
//        {
//            File.Delete(direccionFicheroZIPtemporal);
//        }
//        if (File.Exists(direccionFicheroTXTtemporal))
//        {
//            File.Delete(direccionFicheroTXTtemporal);
//        }
//        if (!File.Exists(direccionFicheroZIPtemporal))
//        {
//            Stream file = File.Create(direccionFicheroZIPtemporal);
//            file.Close();
//        }
//
//        File.WriteAllBytes(this.direccionFicheroZIPtemporal, ficheroDesdeBD);
//        try
//        {
//            using (ZipArchive zip = ZipFile.Open(this.direccionFicheroZIPtemporal, ZipArchiveMode.Read))
//            {
//                int i = 0;
//                if (zip.Entries.Count > 1)
//                {
//                    i = zip.Entries.Count-1;
//                }
//                zip.Entries[i].ExtractToFile(this.direccionFicheroTXTtemporal);
//            }
//
//            //buscar codigoCintaAuditora
//            StreamReader fichero = new StreamReader(this.direccionFicheroTXTtemporal, Encoding.Default);
//            codigoCintaAuditora += fichero.ReadToEnd();
//            fichero.Close();
//        }
//        catch (Exception ex)
//        {
//            codigoCintaAuditora = "";
//        }
//    }
}
