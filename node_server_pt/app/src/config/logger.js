"use strict";

const {createLogger,transports,format, info} = require("winston");
const {combine,colorize,json,timestamp,printf,label,simple} = format;

const printFormat = printf(({timestamp,label,level,message}) => {
    return `${timestamp} [${label}] ${level} : ${message}`;
});

const printLogFormat = {
    file :combine(
        label({
            label: "백엔드 맛보기"
        }),
        timestamp({
            format: "YYYY-MM-DD HH:mm:ss",
        }),
        printFormat,
    ),
    console: combine(
        colorize(),
        simple(),
    ),
};

const opts = {
    file: new transports.File({
        filename: "access.log",
        dirname: "./logs",
        level:"info",
        format: printLogFormat.file,
    }),
    console: new transports.Console({
        level:"debug",
        format: printLogFormat.console,
    })
}

const logger = createLogger({
    transports:[opts.file],
});

if (process.env.NODE_ENV !== "production"){
    logger.add(opts.console);
}

logger.stream = {
    write:(message) => logger.info(message),
};

module.exports = logger;