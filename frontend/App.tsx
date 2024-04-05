import {useState} from "react";
import {MCCService} from "Frontend/generated/endpoints";
import Footer from "Frontend/components/Footer";
import Navbar from "Frontend/components/Navbar";


export default function App() {

    const [dishName, setDishName] = useState('');
    const [recipe, setRecipe] = useState('');
    const [dishImage, setDishImage] = useState('/placeholder.svg');

    const handleGenerateClick = () => {
        if (dishName) {
            MCCService.getRecipes(dishName).then(setRecipe);
            MCCService.getDishImage(dishName).then(setDishImage);
        }
    };

    return (
        <div className="flex flex-col min-h-screen bg-white text-black font-sans">
            <Navbar/>
            <main className="flex flex-col items-center justify-center flex-grow p-16 md:p-32">
                <div className="flex flex-col items-center justify-center space-y-6 w-full md:w-3/4 lg:w-1/2">
                    <h1 className="text-3xl md:text-4xl font-bold tracking-tight">Moroccan Cooking Companion 🇲🇦🍽️</h1>
                    <div
                        className="w-full flex flex-col md:flex-row items-center justify-center space-y-4 md:space-y-0 md:space-x-4">
                        <div className="flex-1">
                            <label className="block mb-2 font-medium text-black-700"
                                   htmlFor="dish-name">
                                Enter the name of a Moroccan dish:
                            </label>
                            <div className="flex items-center space-x-4">
                                <input
                                    className="w-full px-4 py-2 rounded-md border border-gray-300 dark:border-gray-600 dark:bg-[#1A1A1A] dark:text-gray-300 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-blue-500"
                                    id="dish-name"
                                    placeholder="e.g. Tagine, Couscous, Harira"
                                    type="text"
                                    onChange={e => setDishName(e.target.value)}
                                />
                                <button
                                    className="px-6 py-2 bg-blue-500 text-white font-medium rounded-md hover:bg-blue-600 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:ring-opacity-50"
                                    onClick={handleGenerateClick}
                                >
                                    Generate
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
                <div className="mt-8 grid grid-cols-1 md:grid-cols-2 gap-8 w-full md:w-3/4 lg:w-1/2">
                    <div className="h-96 md:h-128 border-2 border-gray-200 rounded-lg p-4">
                        <h2 className="text-2xl font-bold mb-4">Dish Image</h2>
                        <img
                            alt="Moroccan Dish"
                            className="w-full h-full object-cover rounded-lg"
                            src={dishImage}
                        />
                    </div>
                    <div className="h-96 md:h-128 border-2 border-gray-200 rounded-lg p-4 space-y-4">
                        <h2 className="text-2xl font-bold mb-4">Recipes</h2>
                        <pre className="text-gray-700 leading-relaxed whitespace-pre-wrap">
                            {recipe}
                        </pre>
                    </div>
                </div>
            </main>
            <Footer/>
        </div>
    );
}
