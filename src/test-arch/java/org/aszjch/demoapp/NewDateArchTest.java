package org.aszjch.demoapp;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

/**
 * credits to Marek Dominiak
 *
 * @see <a href="https://github.com/trainitek/backtothefuture">source repo</a>
 */
class NewDateArchTest {

    JavaClasses prodClasses = new ClassFileImporter()
            .withImportOption(new ImportOption.DoNotIncludeTests())
            .importPackages("org.aszjch.demoapp");

    @Test
    void checkThatClockIsUsedToCreateDateInProdCode() {
        ArchRuleDefinition.noClasses()
                .should()
                .callMethod(LocalDate.class, "now")
                .orShould()
                .callMethod(LocalDate.class, "now", ZoneId.class)
                .orShould()
                .callMethod(LocalDateTime.class, "now")
                .orShould()
                .callMethod(LocalDateTime.class, "now", ZoneId.class)
                .orShould()
                .callMethod(ZonedDateTime.class, "now")
                .orShould()
                .callMethod(ZonedDateTime.class, "now", ZoneId.class)
                .orShould()
                .callMethod(OffsetDateTime.class, "now")
                .orShould()
                .callMethod(OffsetDateTime.class, "now", ZoneId.class)
                .orShould()
                .callMethod(Instant.class, "now")
                .orShould()
                .callMethod(Clock.class, "fixed", Instant.class, ZoneId.class)
                .orShould()
                .callMethod(Clock.class, "system", ZoneId.class)
                .orShould()
                .callMethod(Clock.class, "systemDefaultZone")
                .orShould()
                .callConstructor(Date.class)
                .because("In prod code we should ALWAYS use Clock to create dates (Our rules in HERE_LIK_TO_WIKI )")
                .check(prodClasses);
    }
}
