FROM node:14.17.3

# Install on /opt/frontend
WORKDIR /opt/frontend

# Copy package.json
COPY package*.json /opt/frontend/

# Install dependencies
RUN npm install

# Copy sources
COPY . /opt/frontend/

# Expose 4200
EXPOSE 4200

# Run application
CMD ["npx", "ng", "serve", "--host", "0.0.0.0"]