FROM node:14.17.3

# Install on /opt/frontend
WORKDIR /opt/frontend

# Copy package.json
COPY package*.json ./

# Install dependencies
RUN npm install

# Copy sources
COPY angular.json ./angular.json
COPY tailwind.config.js ./tailwind.config.js
COPY tsconfig.app.json ./tsconfig.app.json
COPY tsconfig.json ./tsconfig.json
COPY src ./src

# Expose 4200
EXPOSE 4200

# Run application
CMD ["npx", "ng", "serve", "--host", "0.0.0.0", "--poll", "500", "--port", "80"]