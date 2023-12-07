package steved.cinad.services;

import steved.cinad.models.FicheroCintaAuditora;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class FicheroService {
    private final Path rutaTemporal;

    public FicheroService() {
        String guid = UUID.randomUUID().toString();
        rutaTemporal = Paths.get(System.getProperty("user.dir"), "temp").resolve(guid);
    }

    public FicheroCintaAuditora decodificarFichero(byte[] fichero) throws IOException {
        FicheroCintaAuditora ficheroCintaAuditora = null;
        try {
            crearCarpetaTemporal();
            Files.write(rutaTemporal.resolve("temp.zip"), fichero);

            try (ZipInputStream zipInputStream = new ZipInputStream(new ByteArrayInputStream(fichero))) {
                ZipEntry entry;
                while ((entry = zipInputStream.getNextEntry()) != null) {
                    if (!entry.isDirectory()) {
                        String ficheroDecodificado = new String(zipInputStream.readAllBytes(), StandardCharsets.UTF_8);
                        String[] lineas = ficheroDecodificado.split("\n");
                        ficheroCintaAuditora = FicheroCintaAuditora.builder()
                                .name(entry.getName())
                                .byteSize(entry.getSize())
                                .fechaCreacion(new Date (entry.getCreationTime().toMillis()))
                                .datasourse(ficheroDecodificado)
                                .build();
                        ficheroCintaAuditora.setCountLines(ficheroDecodificado);
                        ficheroCintaAuditora.setPaperSizeCentimetros(ficheroDecodificado);
                        ficheroCintaAuditora.setPaperSizeMetros(ficheroDecodificado);
                    }
                }
            }

        } finally {
            eliminarCarpetaTemporal();
        }
        return ficheroCintaAuditora;
    }

    private void eliminarCarpetaTemporal() throws IOException {
        if (Files.exists(rutaTemporal)) {
            Files.walk(rutaTemporal)
                    .sorted(java.util.Comparator.reverseOrder())
                    .map(Path::toFile)
                    .forEach(File::delete);
        }
    }

    private void crearCarpetaTemporal() throws IOException {
        Files.createDirectories(rutaTemporal);
    }
}
