package com.unq.desa.criptoP2P.architectureTest;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;
import static com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices;

@AnalyzeClasses(packages = "com.unq.desa.criptoP2P", importOptions = ImportOption.DoNotIncludeTests.class)
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

    @ArchTest
    public static final ArchRule layerRule =
        layeredArchitecture()
        .consideringAllDependencies()
        .layer("controller").definedBy("com.unq.desa.criptoP2P.controller")
        //.layer("service").definedBy("com.unq.desa.criptoP2P.service")
        //.layer("persistence").definedBy("com.unq.desa.criptoP2P.persistence")

        .whereLayer("controller").mayNotBeAccessedByAnyLayer();
        //.whereLayer("service").mayOnlyBeAccessedByLayers("controller")
       // .whereLayer("persistence").mayOnlyBeAccessedByLayers("service");



}
