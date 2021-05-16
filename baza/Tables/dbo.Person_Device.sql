CREATE TABLE [dbo].[Person_Device] (
  [DEVICE_ID] [int] NOT NULL,
  [MBR_ID] [int] NOT NULL,
  [TYPE_DEVICE] [varchar](20) NOT NULL,
  [NAME_DEVICE] [varchar](30) NOT NULL,
  PRIMARY KEY CLUSTERED ([DEVICE_ID])
)
ON [PRIMARY]
GO

ALTER TABLE [dbo].[Person_Device]
  ADD FOREIGN KEY ([MBR_ID]) REFERENCES [dbo].[Person] ([MBR_ID])
GO