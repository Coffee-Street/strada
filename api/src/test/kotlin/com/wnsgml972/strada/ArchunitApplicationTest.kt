package com.wnsgml972.strada

import com.tngtech.archunit.core.domain.JavaClasses
import com.tngtech.archunit.core.importer.ClassFileImporter
import com.tngtech.archunit.core.importer.ImportOption
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noFields
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

/**
 * 프로젝트 구조에 대한 규칙 검증을 위해 ArchUnit 라이브러리를 사용합니다.
 *
 * 참고: [Spring Boot - Unit Test your project architecture with ArchUnit](https://dzone.com/articles/spring-boot-unit-test-your-project-architecture-wi)
 */
class ArchunitApplicationTest {

    private val importedClasses: JavaClasses = ClassFileImporter()
        .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
        .importPackages("com.wnsgml972.strada")

    @Test
    fun `service 클래스는 controller에서만 접근해야 합니다`() {
        classes()
            .that().resideInAPackage("..service..")
            .should().onlyBeAccessed().byAnyPackage("..service..", "..api..")
            .check(importedClasses)
    }

    @Test
    fun `field injection을 사용하면 안됩니다`() {
        noFields()
            .should().beAnnotatedWith(Autowired::class.java)
            .check(importedClasses)
    }
}