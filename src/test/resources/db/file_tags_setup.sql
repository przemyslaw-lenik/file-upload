INSERT INTO file_body (id, body) VALUES(111, '');
INSERT INTO file_body (id, body) VALUES(112, '');
INSERT INTO file_body (id, body) VALUES(113, '');
INSERT INTO file_body (id, body) VALUES(114, '');
INSERT INTO file_body (id, body) VALUES(115, '');

INSERT INTO file (id, file_name, version, file_body_id) VALUES('c5516b57-0bc4-41a5-870c-257bc58c7679', 'File1', 0, 111);
INSERT INTO file (id, file_name, version, file_body_id) VALUES('672fd08e-4a3d-4fb6-a0a5-2e9b0f40d6f6', 'File2', 0, 112);
INSERT INTO file (id, file_name, version, file_body_id) VALUES('c036a3c8-1acc-40b9-93ff-86965c029e76', 'File3', 0, 113);
INSERT INTO file (id, file_name, version, file_body_id) VALUES('e21a29e4-a96a-4c94-bea0-be5f1c13edd9', 'File4', 0, 114);
INSERT INTO file (id, file_name, version, file_body_id) VALUES('e6852671-9250-40c2-bf02-8e09f4da365b', 'File5', 0, 115);

INSERT INTO tag (id, tag_name, version) VALUES(221, 'Tag1', 0);
INSERT INTO tag (id, tag_name, version) VALUES(222, 'Tag2', 0);
INSERT INTO tag (id, tag_name, version) VALUES(223, 'Tag3', 0);
INSERT INTO tag (id, tag_name, version) VALUES(224, 'Tag4', 0);
INSERT INTO tag (id, tag_name, version) VALUES(225, 'Tag5', 0);

INSERT INTO file_tag (file_id, tag_id) VALUES('c5516b57-0bc4-41a5-870c-257bc58c7679', 221);
INSERT INTO file_tag (file_id, tag_id) VALUES('c5516b57-0bc4-41a5-870c-257bc58c7679', 222);
INSERT INTO file_tag (file_id, tag_id) VALUES('c5516b57-0bc4-41a5-870c-257bc58c7679', 223);
INSERT INTO file_tag (file_id, tag_id) VALUES('c5516b57-0bc4-41a5-870c-257bc58c7679', 225);
INSERT INTO file_tag (file_id, tag_id) VALUES('672fd08e-4a3d-4fb6-a0a5-2e9b0f40d6f6', 222);
INSERT INTO file_tag (file_id, tag_id) VALUES('c036a3c8-1acc-40b9-93ff-86965c029e76', 222);
INSERT INTO file_tag (file_id, tag_id) VALUES('c036a3c8-1acc-40b9-93ff-86965c029e76', 223);
INSERT INTO file_tag (file_id, tag_id) VALUES('c036a3c8-1acc-40b9-93ff-86965c029e76', 225);
INSERT INTO file_tag (file_id, tag_id) VALUES('e21a29e4-a96a-4c94-bea0-be5f1c13edd9', 222);
INSERT INTO file_tag (file_id, tag_id) VALUES('e21a29e4-a96a-4c94-bea0-be5f1c13edd9', 223);
INSERT INTO file_tag (file_id, tag_id) VALUES('e21a29e4-a96a-4c94-bea0-be5f1c13edd9', 224);
INSERT INTO file_tag (file_id, tag_id) VALUES('e21a29e4-a96a-4c94-bea0-be5f1c13edd9', 225);
INSERT INTO file_tag (file_id, tag_id) VALUES('e6852671-9250-40c2-bf02-8e09f4da365b', 223);
INSERT INTO file_tag (file_id, tag_id) VALUES('e6852671-9250-40c2-bf02-8e09f4da365b', 224);