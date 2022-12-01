package com.unq.desa.criptoP2P.architectureTest;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.springframework.stereotype.Service;


import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(packages = "com.unq.desa.criptoP2P")
public class architectureTests {


    @ArchTest
    static ArchRule services_must_be_in_correct_package =
            classes()
                    .that().haveSimpleNameEndingWith("Service")
                    .should().resideInAPackage("com.unq.desa.criptoP2P.service");
    @ArchTest
    static ArchRule controllers_must_be_in_correct_package =
            classes()
                    .that().haveSimpleNameEndingWith("Controller")
                    .should().resideInAPackage("com.unq.desa.criptoP2P.controller");

    @ArchTest
    static ArchRule Repositories_must_be_in_correct_package =
            classes()
                    .that().haveSimpleNameEndingWith("Repository")
                    .should().resideInAPackage("com.unq.desa.criptoP2P.persistence");

    @ArchTest
    public static final ArchRule controllersShouldBeSuffixed = classes()
            .that().resideInAPackage("com.unq.desa.criptoP2P.controller")
            .should().haveSimpleNameEndingWith("Controller");
    @ArchTest
    public static final ArchRule servicesShouldBeSuffixed = classes()
            .that().resideInAPackage("com.unq.desa.criptoP2P.service")
            .should().haveSimpleNameEndingWith("Service");
    @ArchTest
    public static final ArchRule persistencesShouldBeSuffixed = classes()
            .that().resideInAPackage("com.unq.desa.criptoP2P.persistence")
            .should().haveSimpleNameEndingWith("Repository");


}
