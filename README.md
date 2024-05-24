### testing-h2-database er et startprojekt, der demonstrerer integrationstests af en repository-klasse ved brug af en h2 in-memory-database

Projektet adresserer et problem, som nogle har oplevet i deres egne projekter, hvor testene består, når de køres enkeltvis, men fejler, når de køres samlet. 
Fejlene opstår, fordi H2-databasen ikke bliver nulstillet før hver testmetode køres. Anvendelse af Springs testannotering `@DirtiesContext` med f.eks. modes `BEFORE_EACH_TEST_METHOD` eller `BEFORE_METHOD` har ikke nødvendigvis løst problemet.

I projektet `testing-h2-database` er testklassen `ProjectRepositoryTest` annoteret med:

`@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:h2schema.sql")`

Annoteringen sikrer, at databasen er nulstillet, inden hver enkelt testmetode køres.

`h2schema.sql` er et script i `test/resources-mappen`, som:
- opretter skemaet, hvis det ikke findes
- dropper tabellen, hvis den findes
- opretter en ny tabel
- indsætter data i tabellen
