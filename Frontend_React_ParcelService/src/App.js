import {
    Box,
    Button,
    ButtonGroup,
    Flex,
    HStack,
    IconButton,
    Input,
    SkeletonText,
    Text,
} from "@chakra-ui/react";
import { FaLocationArrow, FaTimes } from "react-icons/fa";
import axios from "axios";
import { BrowserRouter, Routes, Route } from "react-router-dom"; // to connect browserurl to react application
import home from "./home";
import findOrder from "./findOrder";
import { useState, useRef, useEffect } from "react";

import {
    useJsApiLoader,
    GoogleMap,
    Marker,
    Autocomplete,
    DirectionsRenderer,
} from "@react-google-maps/api";

const center = { lat: 48.8584, lng: 2.2945 };

const App = () => {
    const { isLoaded } = useJsApiLoader({
        googleMapsApiKey: process.env.REACT_APP_GOOGLE_MAPS_API_KEY,
        libraries: ["places"],
    });

    const [map, setMap] = useState(
        /** @type google.maps.Map */ (null)
    ); /* to pan back to the center. this fix has been made */
    const [directionsResponse, setDirectionsResponse] = useState(null);
    const [distance, setDistance] = useState("");
    const [duration, setDuration] = useState("");
    const [textboxValue, settextboxValue] = useState(null);
    /** @type React.MutableRefObject<HTMLInputElement> */
    const originRef = useRef();
    /** @type React.MutableRefObject<HTMLInputElement> */
    const destiantionRef = useRef();
    const [pointerLocation, setpointerLocation] = useState({
        lat: 52.50742,
        lng: 13.2821,
    });
    const [executedOnce,setExecutedOnce] = useState(false);
    useEffect(() => {}, []);

    if (!isLoaded) {
        return <SkeletonText />;
    }

    async function searchOrder() {
        /*Here rest call will be made */
        const readURL = "http://localhost:7080/read/" + textboxValue;
        const readGpsCoordinates = "http://localhost:7080/listCoordinates";

        axios.get(readURL).then((response) => {
            originRef.current.value = response.data.currentCity;
            destiantionRef.current.value = response.data.destinationCity;
        });

        if (
            originRef.current.value === "" ||
            destiantionRef.current.value === ""
        ) {
            return;
        }

        if (!executedOnce) {
            setExecutedOnce(true);
            axios.get(readGpsCoordinates).then((allCoordinates) => {
                let markerupdation = 0;
                const motion = setInterval(() => {
                    markerSetter(allCoordinates.data[markerupdation]);
                    markerupdation = markerupdation + 1;
                    if (markerupdation > allCoordinates.data.length) {
                        clearInterval(motion);
                        setExecutedOnce(false);
                    }
                }, 10);
            });
        }

        // eslint-disable-next-line no-undef
        const directionsService = await new google.maps.DirectionsService();
        const results = directionsService.route({
            origin: originRef.current.value,
            destination: destiantionRef.current.value,
            // eslint-disable-next-line no-undef
            travelMode: google.maps.TravelMode.DRIVING,
        });

        results.then((data) => {
            setDirectionsResponse(data);
            setDistance(data.routes[0].legs[0].distance.text);
            setDuration(data.routes[0].legs[0].duration.text);
        });
    }

    function calculateRoute() {}

    function markerSetter(markerLocation) {
        setpointerLocation(() => {
            return {
                lat: parseFloat(markerLocation.lng),
                lng: parseFloat(markerLocation.ln),
            };
        });
    }

    function clearRoute() {
        setDirectionsResponse(null);
        setDistance("");
        setDuration("");
        originRef.current.value = "";
        destiantionRef.current.value = "";
    }

    function getData(val) {
        settextboxValue(val.target.value);
    }

    //I will fetch the gpsCoordinates from backend here

    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={home}></Route>
                <Route path="/about" element={findOrder}></Route>
            </Routes>
            <Flex
                position="relative"
                flexDirection="column"
                alignItems="center"
                h="100vh"
                w="100vw"
            >
                <Box position="absolute" left={0} top={0} h="100%" w="100%">
                    <GoogleMap
                        center={center}
                        zoom={15}
                        mapContainerStyle={{
                            width: "100%",
                            height: "100%",
                        }}
                        options={{
                            zoomControl: false,
                            streetViewControl: false,
                            mapTypeControl: false,
                            fullscreenControl: false,
                        }}
                        onLoad={(map) => setMap(map)}
                    >
                        {<Marker position={pointerLocation} />}

                        {/* <Marker position={center} /> */}
                        {directionsResponse && (
                            <DirectionsRenderer
                                directions={directionsResponse}
                            />
                        )}
                    </GoogleMap>
                </Box>
                <Box
                    p={4}
                    borderRadius="lg"
                    m={4}
                    bgColor="white"
                    shadow="base"
                    minW="container.md"
                    zIndex="1"
                >
                    <HStack spacing={2} justifyContent="space-between">
                        <Box flexGrow={1}>
                            <Input
                                type="text"
                                placeholder="Order_ID"
                                onChange={getData}
                            />
                            <IconButton
                                onClick={() => {
                                    searchOrder();
                                }}
                                aria-label="center back"
                                icon={<FaLocationArrow />}
                            />
                        </Box>
                        <Box flexGrow={1}>
                            <Autocomplete>
                                <Input
                                    type="text"
                                    placeholder="Origin"
                                    ref={originRef}
                                />
                            </Autocomplete>
                        </Box>
                        <Box flexGrow={1}>
                            <Autocomplete>
                                <Input
                                    type="text"
                                    placeholder="Destination"
                                    ref={destiantionRef}
                                />
                            </Autocomplete>
                        </Box>

                        <ButtonGroup>
                           
                            <IconButton
                                aria-label="center back"
                                icon={<FaTimes />}
                                onClick={clearRoute}
                            />
                        </ButtonGroup>

                        
                    </HStack>
                    <HStack spacing={4} mt={4} justifyContent="space-between">
                        <Text>Distance: {distance} </Text>
                        <Text>Duration: {duration} </Text>
                        <IconButton
                            aria-label="center back"
                            icon={<FaLocationArrow />}
                            isRound
                            onClick={() => {
                                map.panTo(
                                    center
                                ); /* this map was not getting panTo options And I had to alter useState map */
                                map.setZoom(
                                    15
                                ); /*the center value is hard coded for now */
                            }}
                        />
                    </HStack>
                </Box>
            </Flex>
        </BrowserRouter>
    );
};

export default App;
