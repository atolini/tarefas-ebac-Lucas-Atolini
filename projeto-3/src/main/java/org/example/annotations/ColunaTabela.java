package org.example.annotations;

import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ColunaTabela {
    String dbName();
    String setJavaName();
    String getJavaName();
}
