CREATE TABLE [dbo].[Measurement] (
  [MBR_ID] [int] NOT NULL,
  [DEVICE_ID] [int] NOT NULL,
  [TIME_MEASUREMENT] [smalldatetime] NULL,
  [TYPE_MEASUREMENT] [varchar](30) NULL,
  [VALUE_MEASUREMENT] [varchar](30) NULL
)
ON [PRIMARY]
GO

ALTER TABLE [dbo].[Measurement]
  ADD FOREIGN KEY ([DEVICE_ID]) REFERENCES [dbo].[Person_Device] ([DEVICE_ID])
GO

ALTER TABLE [dbo].[Measurement]
  ADD FOREIGN KEY ([MBR_ID]) REFERENCES [dbo].[Person] ([MBR_ID])
GO