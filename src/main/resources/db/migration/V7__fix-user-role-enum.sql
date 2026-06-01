-- Ajusta o enum do Postgres para bater com o Java (UserRole: ADMIN/EMPLOYEE)
-- Migração segura: cria novo tipo, converte coluna, remove tipo antigo e renomeia.

DO $$
BEGIN
    IF EXISTS (SELECT 1 FROM pg_type WHERE typname = 'user_role') THEN
        CREATE TYPE user_role_new AS ENUM ('ADMIN', 'EMPLOYEE');

        ALTER TABLE users
            ALTER COLUMN role TYPE user_role_new
            USING (
                CASE lower(role::text)
                    WHEN 'admin' THEN 'ADMIN'::user_role_new
                    ELSE 'EMPLOYEE'::user_role_new
                END
            );

        DROP TYPE user_role;
        ALTER TYPE user_role_new RENAME TO user_role;
    END IF;
END $$;

