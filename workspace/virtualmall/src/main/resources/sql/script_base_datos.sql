/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  CARLOS_CODESOFT
 * Created: 29/03/2020
 */

CREATE TABLE CATEGORIA(
    ID BIGINT NOT NULL AUTO_INCREMENT,
    NOMBRE VARCHAR(120),
    DESCRIPCION VARCHAR(1024),    
    IMAGEN VARCHAR(512),
    ESTADO VARCHAR(1),
    FECHA_CREACION_REGISTRO DATE,

    PRIMARY KEY (ID)
);

CREATE TABLE SUBCATEGORIA
(
    ID BIGINT NOT NULL AUTO_INCREMENT,
    CATEGORIA_ID BIGINT,
    NOMBRE VARCHAR(120),
    ESTADO VARCHAR(1),
    FECHA_CREACION_REGISTRO DATE,

    PRIMARY KEY (ID),
    FOREIGN KEY (CATEGORIA_ID) REFERENCES CATEGORIA(ID)
);

CREATE TABLE USUARIO
(   
    ID BIGINT NOT NULL AUTO_INCREMENT,
    NICK VARCHAR(120),
    CLAVE VARCHAR(1024),    
    TIPO VARCHAR(1),    
    ESTADO VARCHAR(1),  
    FECHA_CREACION_REGISTRO DATE,
    
    PRIMARY KEY (ID)
);

CREATE TABLE PROVEEDOR
(
    ID BIGINT NOT NULL AUTO_INCREMENT,
    USUARIO_ID BIGINT,
    IDENTIFICACION VARCHAR(15),
    NOMBRES VARCHAR(256),
    APELLIDOS VARCHAR(256),
    DIRECCION VARCHAR(256),
    CORREO VARCHAR(256),
    WHATSAPP VARCHAR(16),
    TELEFONO2 VARCHAR(16),
    TELEFONO3 VARCHAR(16),
    NOMBRE_NEGOCIO VARCHAR(256),
    DESCRIPCION_NEGOCIO VARCHAR(1024),
    VALORES_PAGAR DECIMAL(22,4),
    FECHA_CORTE_PAGO DATE,    
    ESTADO VARCHAR(1),
    FECHA_CREACION_REGISTRO DATE,
    

   PRIMARY KEY (ID),
   FOREIGN KEY (USUARIO_ID) REFERENCES USUARIO(ID)
    
);

CREATE TABLE SUBCATEGORIA_PROVEEDOR
(
    ID BIGINT NOT NULL AUTO_INCREMENT,
    PROVEEDOR_ID BIGINT,
    SUBCATEGORIA_ID BIGINT,
    ESTADO VARCHAR(1),
    FECHA_CREACION_REGISTRO DATE,
    
    PRIMARY KEY (ID),
    FOREIGN KEY (PROVEEDOR_ID) REFERENCES PROVEEDOR(ID),
    FOREIGN KEY (SUBCATEGORIA_ID) REFERENCES SUBCATEGORIA(ID)
);

CREATE TABLE SOLICITUD_BUSQUEDA
(
    ID BIGINT NOT NULL AUTO_INCREMENT,
    BUSQUEDA VARCHAR(2024),
    NOMBRES_CLIENTE VARCHAR(512),
    WHATSAPP_CLIENTE VARCHAR(16),
    CORREO_CLIENTE VARCHAR(256),
    TIEMPO_RESPUESTA_MIN BIGINT,
    FECHA_HORA_SOLICITUD DATETIME,
    ESTADO VARCHAR(1),
    FECHA_CREACION_REGISTRO DATE,

    PRIMARY KEY (ID)

);

CREATE TABLE SUBCATEGORIA_BUSQUEDA
(
    ID BIGINT NOT NULL AUTO_INCREMENT,
    PROVEEDOR_ID BIGINT,
    SUBCATEGORIA_ID BIGINT,
    ESTADO VARCHAR(1),
    FECHA_CREACION_REGISTRO DATE,
    
    PRIMARY KEY (ID),
    FOREIGN KEY (PROVEEDOR_ID) REFERENCES PROVEEDOR(ID),
    FOREIGN KEY (SUBCATEGORIA_ID) REFERENCES SUBCATEGORIA(ID)
);


CREATE TABLE PRESUPUESTO
(
    ID BIGINT NOT NULL AUTO_INCREMENT,
    PROVEEDOR_ID BIGINT,
    SOLICITUD_BUSQUEDA_ID BIGINT,
    DESCRIPCION VARCHAR(5000),
    FOTO1 VARCHAR(512),
    FOTO2 VARCHAR(512),
    FOTO3 VARCHAR(512),
    VALOR DECIMAL(22,4),
    NUEVO_O_USADO VARCHAR(1),
    
    ESTADO VARCHAR(1),
    FECHA_CREACION_REGISTRO DATE,

    PRIMARY KEY (ID),
    FOREIGN KEY (PROVEEDOR_ID) REFERENCES PROVEEDOR(ID),
    FOREIGN KEY (SOLICITUD_BUSQUEDA_ID) REFERENCES SOLICITUD_BUSQUEDA(ID)
);


CREATE TABLE PARAMETROS
(
    ID BIGINT NOT NULL AUTO_INCREMENT,
    NOMBRE VARCHAR(256),
    DESCRIPCION VARCHAR(2048),
    ESTADO VARCHAR(1),
    FECHA_CREACION_REGISTRO DATE,

    PRIMARY KEY (ID)
);