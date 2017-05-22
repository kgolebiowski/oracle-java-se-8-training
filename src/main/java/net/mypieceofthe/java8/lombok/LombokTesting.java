package net.mypieceofthe.java8.lombok;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.util.Arrays;
import java.util.List;

/**
 * Created by kgolebiowski on 22/05/2017.
 */
@Data
@Builder
public class LombokTesting {

    private String someStringField;

    private int someIntField;

    private List<String> stringsList;

    public static void main(String[] args) {
        LombokTesting buildObject = LombokTesting.builder()
                .someIntField(10)
                .someStringField("Hello")
                .stringsList(Arrays.asList("First", "Second", "Third"))
                .build();

        System.out.println(buildObject.getSomeStringField());
        System.out.println(buildObject.toString());

        try {
            buildObject.someMethodWithNonNullArguments(null);
        } catch (NullPointerException e) {
            System.out.println("It was so null!!");
        }
    }

    void someMethodWithNonNullArguments(@NonNull String notNullString) { }
}
