# springPractice00
####to celebrate that changed the IDE (from Eclipse to IntelliJ),I created the simple spring project.(actually... to practice the SPRING)

I guess this project will be simple board project included some functions that reply, file IO, and so on
```
CREATE TABLE TBL_BOARD (
  BGNO INT(11),								-- board group no
  BRDNO int(11) NOT NULL AUTO_INCREMENT,	-- article number
  BRDTITLE varchar(255),						-- title
  BRDWRITER varchar(20),						-- writer
  BRDMEMO   varchar(4000),					-- content
  BRDDATE	datetime,							-- date
  BRDHIT INT,									-- read count
  BRDDELETEFLAG CHAR(1),						-- delete flag
  PRIMARY KEY (BRDNO)
) ;

CREATE TABLE TBL_BOARDFILE (
    FILENO INT(11)  NOT NULL AUTO_INCREMENT, -- file number
    BRDNO INT(11),                           -- article number
    FILENAME VARCHAR(100),                   -- file name
    REALNAME VARCHAR(30),                    -- real name
    FILESIZE INT,                            -- file size
    PRIMARY KEY (FILENO)
);

CREATE TABLE TBL_BOARDREPLY (
    BRDNO INT(11) NOT NULL,					-- article number
    RENO INT(11) NOT NULL,                 -- reply number
    REWRITER VARCHAR(10) NOT NULL,         -- writer
    REMEMO VARCHAR(500) DEFAULT NULL,      -- reply content
    REDATE DATETIME DEFAULT NULL,          -- date
    REDELETEFLAG VARCHAR(1) NOT NULL,      -- delete flag
    REPARENT INT(11),							-- parent reply
    REDEPTH INT,								-- deep	
    REORDER INT,								-- order
    PRIMARY KEY (RENO)
);

CREATE TABLE TBL_BOARDGROUP (
  BGNO INT(11) NOT NULL AUTO_INCREMENT,		-- board group number
  BGNAME VARCHAR(50),							-- board group name
  BGPARENT INT(11),							-- group parent
  BGDELETEFLAG CHAR(1),						-- delete flag
  BGUSED CHAR(1),								-- board using flag
  BGREPLY CHAR(1),							-- reply using flag
  BGREADONLY CHAR(1),							-- writable flag
  BGDATE DATETIME,							-- date
  PRIMARY KEY (BGNO)
);
```
