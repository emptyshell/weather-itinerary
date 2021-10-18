import axios from "axios";

export const fetchCityWeather = (weather) => {
    return axios.get(`http://localhost:8080/weather/fetch?city=${weather}`);
};

export const addItinerary = (name, city) => {
    return axios.post(`http://localhost:8080/itinerary/add`, {name, city});
};

export const getItinerary = (name) => {
    return axios.get(`http://localhost:8080/itinerary/${name}`);
};

export const generateSummary = (itineraryOrCityName) => {
    return axios.get(`http://localhost:8080/summary/${itineraryOrCityName}`);
}