package task.first.implementations;

import task.first.interfaces.UploadProperty;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Properties;

public class UploadPropertyImpl implements UploadProperty {
    @Override
    public Properties upload(Properties property) {
        try (Reader reader = new FileReader("src/main/resources/application.properties")) {
            property.load(reader);
        } catch (IOException e) {
            System.out.println("Ошибка при загрузке файла, возможно не правильно указан путь к файлу \n" +
                    "-> будут установлены значения по умолчанию!\n\n");
        }
        return property;
    }
}
