﻿CREATE TABLE [dbo].[Person_Login] (
  [MBR_ID] [int] NOT NULL,
  [PERSON_USERNAME] [varchar](30) NOT NULL,
  [PERSON_PASSWORD] [varchar](30) NOT NULL,
  UNIQUE ([PERSON_USERNAME]),
  CONSTRAINT [PasswordLength] CHECK (len([PERSON_PASSWORD])>=(8) AND len([PERSON_PASSWORD])<=(30))
)
ON [PRIMARY]
GO

ALTER TABLE [dbo].[Person_Login]
  ADD FOREIGN KEY ([MBR_ID]) REFERENCES [dbo].[Person] ([MBR_ID])
GO