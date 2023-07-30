CREATE TABLE users (
	"id" uuid NOT NULL unique,
	"name" varchar(100) NOT NULL,
	"email_address" varchar(50) NOT NULL UNIQUE,
	"password" varchar(50) NOT NULL,
    "active" boolean DEFAULT true,
    "customer_id" varchar(50) null
);


CREATE TABLE product_plans (
	"id" uuid NOT NULL unique,
	"price_id" varchar(100),
	"product_id" varchar(50),
	"product_name" varchar(50),
    "amount" numeric,
    "active" boolean DEFAULT true
);

CREATE TABLE products_bought (
	"id" uuid NOT NULL,
	"product_id" UUID NOT NULL,
	"user_id" UUID NOT NULL,
    "quantity" numeric,
    "paid" boolean DEFAULT false,
    CONSTRAINT fk_product_bought FOREIGN KEY ("product_id") REFERENCES product_plans (id),
    CONSTRAINT fk_users FOREIGN KEY ("user_id") REFERENCES users (id)
);

