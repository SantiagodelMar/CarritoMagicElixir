// Servidor local sin dependencias: node servidor.js  ->  http://localhost:5500
const http = require("http");
const fs = require("fs");
const path = require("path");

const PUERTO = Number(process.env.PORT) || 5500;
const RAIZ = __dirname;
const TIPOS = {
    ".html": "text/html; charset=utf-8",
    ".css": "text/css; charset=utf-8",
    ".js": "text/javascript; charset=utf-8",
    ".png": "image/png",
    ".ttf": "font/ttf",
    ".md": "text/markdown; charset=utf-8",
};

http.createServer((pedido, respuesta) => {
    const ruta = decodeURIComponent(new URL(pedido.url, "http://localhost").pathname);
    const archivo = path.join(RAIZ, ruta === "/" ? "index.html" : ruta);
    if (!archivo.startsWith(RAIZ)) {
        respuesta.writeHead(403).end();
        return;
    }
    fs.readFile(archivo, (error, contenido) => {
        if (error) {
            respuesta.writeHead(404).end("No encontrado");
            return;
        }
        respuesta.writeHead(200, { "Content-Type": TIPOS[path.extname(archivo)] || "application/octet-stream" });
        respuesta.end(contenido);
    });
}).listen(PUERTO, () => console.log("Magic Elixir en http://localhost:" + PUERTO));
