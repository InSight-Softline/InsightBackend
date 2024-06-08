-- Creates the table 'audits' if it does not exist
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[audits]') AND type in (N'U'))
BEGIN
    CREATE TABLE audits (
        id INT PRIMARY KEY,
        name VARCHAR(100) NOT NULL
    );
END;
GO

-- Creates the table 'questions' if it does not exist
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[questions]') AND type in (N'U'))
BEGIN
    CREATE TABLE questions (
        id INT PRIMARY KEY,
        name VARCHAR(100) NOT NULL
    );
END;
GO

-- Creates the table 'categories' if it does not exist
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[categories]') AND type in (N'U'))
BEGIN
    CREATE TABLE categories (
        id INT PRIMARY KEY,
        name VARCHAR(100) NOT NULL
    );
END;
GO

-- Creates the table 'ratings' if it does not exist
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[ratings]') AND type in (N'U'))
BEGIN
    CREATE TABLE ratings (
        id INT PRIMARY KEY,
        name VARCHAR(100) NOT NULL,
        points INT CHECK (points BETWEEN 0 AND 5) NOT NULL,
        comment VARCHAR(500) NOT NULL,
        na BIT NOT NULL,
        audit_id INT NOT NULL,
        question_id INT NOT NULL,
        FOREIGN KEY (audit_id) REFERENCES audits(id) NOT NULL,
        FOREIGN KEY (question_id) REFERENCES questions(id) NOT NULL
    );
END;
GO
