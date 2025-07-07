package com.yarovyi.app;

import com.yarovyi.app.annotationProcessing.annotation.RequiredField;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    @RequiredField("id")
    private String id;

    @RequiredField("name")
    private String name;

    @RequiredField("surname")
    private String surname;

    @RequiredField("age")
    private String age;

    @RequiredField
    private String taxcode;

    @Override
    public String toString() {
        return "Person{" +
               "id='" + id + '\'' +
               ", name='" + name + '\'' +
               ", surname='" + surname + '\'' +
               ", age='" + age + '\'' +
               ", taxcode='" + taxcode + '\'' +
               '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id)
               && Objects.equals(name, person.name)
               && Objects.equals(surname, person.surname)
               && Objects.equals(age, person.age)
               && Objects.equals(taxcode, person.taxcode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, age, taxcode);
    }

}
