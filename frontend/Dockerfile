FROM node:16

# Create app directory
WORKDIR /usr/src/app

COPY package*.json ./

COPY . .

RUN npm ci
RUN npm run build

EXPOSE 8080
CMD [ "npx", "--yes", "serve", "-s", "-p", "8080", "build" ]

