-- Creates the table 'audits' if it does not exist
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[audits]') AND type in (N'U'))
BEGIN
    CREATE TABLE audits (
        id INT PRIMARY KEY,
        name VARCHAR(100)
    );
END;
GO

-- Creates the table 'questions' if it does not exist
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[questions]') AND type in (N'U'))
BEGIN
    CREATE TABLE questions (
        id INT PRIMARY KEY,
        name VARCHAR(100)
    );
END;
GO

-- Creates the table 'categories' if it does not exist
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[categories]') AND type in (N'U'))
BEGIN
    CREATE TABLE categories (
        id INT PRIMARY KEY,
        name VARCHAR(100)
    );
END;
GO

-- Creates the table 'ratings' if it does not exist
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[ratings]') AND type in (N'U'))
BEGIN
    CREATE TABLE ratings (
        id INT PRIMARY KEY,
        name VARCHAR(100),
        points INT CHECK (points BETWEEN 0 AND 5),
        comment VARCHAR(500),
        na BIT,
        audit_id INT,
        question_id INT,
        FOREIGN KEY (audit_id) REFERENCES audits(id),
        FOREIGN KEY (question_id) REFERENCES questions(id)
    );
END;
GO
