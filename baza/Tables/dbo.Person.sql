CREATE TABLE [dbo].[Person] (
  [MBR_ID] [int] NOT NULL,
  [FIRST_NAME] [varchar](30) NOT NULL,
  [LAST_NAME] [varchar](30) NOT NULL,
  [DATE_BIRTH] [date] NOT NULL,
  [HEIGHT] [int] NOT NULL,
  [WEIGHT] [int] NOT NULL,
  [EMAIL] [varchar](50) NOT NULL,
  PRIMARY KEY CLUSTERED ([MBR_ID])
)
ON [PRIMARY]
GO