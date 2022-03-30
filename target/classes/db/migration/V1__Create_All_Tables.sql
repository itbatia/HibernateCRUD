CREATE TABLE public.tag (
	id INT NOT NULL GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
	name VARCHAR(50)
);

CREATE TABLE public.writer (
	id INT NOT NULL GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
	name VARCHAR(50)
);

CREATE TYPE status AS ENUM ('DELETED', 'ACTIVE');

CREATE TABLE public.poststatus(
    id integer NOT NULL,
    status status NOT NULL,
    CONSTRAINT poststatus_pkey PRIMARY KEY (id)
);

INSERT INTO poststatus (id, status) VALUES (0, 'DELETED');
INSERT INTO poststatus (id, status) VALUES (1, 'ACTIVE');

CREATE TABLE public.post (
	id INT NOT NULL GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
	content TEXT,
	post_status INT,
	writer_id INT,
	FOREIGN KEY (writer_id) REFERENCES writer (id) ON DELETE CASCADE,
	FOREIGN KEY (post_status) REFERENCES poststatus (id) ON DELETE CASCADE
);

CREATE TABLE public.post_tag (
	post_id INT,
	tag_id INT,
	FOREIGN KEY (post_id) REFERENCES post (id) ON DELETE CASCADE,
	FOREIGN KEY (tag_id) REFERENCES tag (id) ON DELETE CASCADE
);