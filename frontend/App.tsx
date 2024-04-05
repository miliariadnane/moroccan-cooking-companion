import {Fragment, useState} from "react";
import {MCCService} from "Frontend/generated/endpoints";
import Footer from "Frontend/components/Footer";
import Navbar from "Frontend/components/Navbar";
import ReactMarkdown from 'react-markdown';


export default function App() {

    const [dishName, setDishName] = useState('');
    const [recipe, setRecipe] = useState('');
    const [dishImage, setDishImage] = useState('');

    const handleGenerateClick = () => {
        if (dishName) {
            MCCService.getRecipes(dishName).then(setRecipe);
            MCCService.getDishImage(dishName).then(setDishImage);
        }
    };

    return (
            <div className="flex min-h-screen flex-col bg-white font-sans text-black">
                <Navbar/>
                <main
                    className="mx-auto flex-grow flex-col py-16 px-4 sm:px-6 lg:px-8">
                    <div className="flex flex-col items-center justify-center space-y-6">
                        <h1 className="text-3xl font-bold tracking-tight md:text-4xl">
                            Moroccan Cooking Companion 🇲🇦🍽️
                        </h1>
                        <div
                            className="flex w-full flex-col items-center justify-center space-y-4 md:flex-row md:space-x-4 md:space-y-0">
                        <div className="flex-1 max-w-3xl">
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
                    <div className="mt-8 grid grid-cols-1 gap-8 md:grid-cols-5">
                        <div className="col-span-2 rounded-lg border-2 border-gray-200 p-4">
                            <h2 className="mb-4 text-2xl font-bold">Dish Image</h2>
                            <img
                                alt="Moroccan Dish"
                                className="aspect-square min-h-80 rounded-lg object-cover"
                            src={dishImage}
                        />
                    </div>
                    <div className="col-span-3 space-y-4 rounded-lg border-2 border-gray-200 p-4">
                        <h2 className="mb-4 text-2xl font-bold">Recipes</h2>
                        <ReactMarkdown className="whitespace-pre-wrap leading-relaxed text-gray-700">
                            {recipe}
                        </ReactMarkdown>
                    </div>
                    </div>
                </main>
                <Footer/>
        </div>
    );
}
